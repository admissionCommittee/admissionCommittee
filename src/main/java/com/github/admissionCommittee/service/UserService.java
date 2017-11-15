package com.github.admissionCommittee.service;

import com.github.admissionCommittee.dao.DaoFactory;
import com.github.admissionCommittee.dao.UserDao;
import com.github.admissionCommittee.model.Faculty;
import com.github.admissionCommittee.model.Sheet;
import com.github.admissionCommittee.model.User;
import com.github.admissionCommittee.util.validate.UserValidatorUtil;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import static com.github.admissionCommittee.model.enums.UserAttendeeState
        .NONPARTICIPANT;
import static com.github.admissionCommittee.model.enums.UserAttendeeState
        .EXCLUDED;
import static com.github.admissionCommittee.model.enums.UserAttendeeState
        .STUDENT;

public class UserService extends GenericService<User> {
    private UserValidatorUtil validator = new UserValidatorUtil();
    private SheetService sheetService = new SheetService();

    public UserService() {
        super(User.class, DaoFactory.getDaoFactory().getUserDao());
    }

    @Override
    public void save(User instance) {
        validator.validate(instance);
        super.save(instance);
    }

    @Override
    public void save(List<User> instance) {
        instance.forEach(validator::validate);
        super.save(instance);
    }

    //for DB search by mail implementation
    public User getByMail(String mail) {
        return ((UserDao) getDao()).getByMail(mail);
    }

    //get only attendees that satisfy the attendee demands
    public Map<Faculty, List<User>> getEnlistedAttendees() {
        Map<Faculty, List<User>> approvedMap = new HashMap<>();
        List<Faculty> facultyList = new FacultyService()
                .getAll();
        facultyList.sort(Comparator.comparing(Faculty::getName));
        facultyList.forEach(faculty -> {
            approvedMap.put(faculty, getApprovedSheets(sheetService.getByFaculty
                            (faculty),
                    faculty.getPeopleLimit()));
        });

        //updateIsEnlisted
        // updateIsEnlisted(approvedMap);
        return approvedMap;
    }

    public List<User> getByFacultyEnlisted(Faculty faculty){
        return getEnlistedAttendees().get(faculty);
    }

    public List<User> getApprovedSheets(List<Sheet> sheetList,
                                        Integer attendeesLimit) {
        //getApprovedSheets - that belonging to successful attendees
        Map<Integer, Sheet> userMap = new TreeMap<>();
        sheetList.forEach(element -> {
            if (userMap.get(element.getSumExamCertificateScore())
                    == null) {
                userMap.put(element.getSumExamCertificateScore(),
                        element);
            } else {
                if (element.getUser().getSchoolCertificate().getAverageScore() >
                    userMap.get(element.getSumExamCertificateScore()).getUser()
                        .getSchoolCertificate().getAverageScore()) {
                    userMap.put(element.getSumExamCertificateScore(),
                            element);
                }
            }
        });

        //check attendees limit and delete others
        Iterator iterator = userMap.entrySet().iterator();
        int counter = 0;
        while (iterator.hasNext() && counter < userMap.size() -
                attendeesLimit) {
            iterator.remove();
            counter++;
        }

        List<User> students = userMap.values().stream().map
                (Sheet::getUser)
                .collect(Collectors.toList());

        //updateAttendeeState
        updateAttendeeState(students);
        return students;
    }

    //updateAttendeeState
    public void updateAttendeeState(List<User> students) {
        List<User> allUsers = getAll();
        students.forEach(user -> {
            if (students.contains(user)) {
                user.setUserAttendeeState(STUDENT);
            } else {
                if (user.getUserAttendeeState() != NONPARTICIPANT) {
                    user.setUserAttendeeState(EXCLUDED);
                }
            }
        });
        save(allUsers);
    }
}