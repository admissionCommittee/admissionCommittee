package com.github.admissionCommittee.service;

import com.github.admissionCommittee.dao.DaoFactory;
import com.github.admissionCommittee.model.Faculty;
import com.github.admissionCommittee.model.Sheet;
import com.github.admissionCommittee.model.User;
import com.github.admissionCommittee.dao.UserDao;

import javax.jws.soap.SOAPBinding;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class UserService extends GenericService<User> {

    public SheetService sheetService = new SheetService();

    public UserService() {
        super(User.class, DaoFactory.getDaoFactory().getUserDao());
    }

    //for DB search by mail implementation
    public User getByMail(String mail) {
        return ((UserDao) getDao()).getByMail(mail);
    }

    public void updateIsEnlisted(Map<Faculty, List<User>> facultyAttendeeList) {
        facultyAttendeeList.values().parallelStream()
                .forEachOrdered(list -> list.parallelStream().forEachOrdered(user -> user
                        .setEnlisted(true)));
    }

    //get only attendees that satisfy the attendee demands
    public Map<Faculty, List<User>> getEnlistedAttendees() {
        Map<Faculty, List<User>> approvedMap = new HashMap<>();
        List<Faculty> facultyList = new FacultyService()
                .getAll();
        facultyList.sort(Comparator.comparing(Faculty::getName));
        facultyList.parallelStream().forEachOrdered(faculty -> {
            approvedMap.put(faculty, getApprovedSheets(sheetService.getByFaculty
                            (faculty),
                    faculty.getPeopleLimit()));
        });

        //updateIsEnlisted
        updateIsEnlisted(approvedMap);
        return approvedMap;
    }

    public List<User> getApprovedSheets(List<Sheet> sheetList,
                                        Integer attendeesLimit) {
        //getApprovedSheets - that belonging to successful attendees
        Map<Integer, Sheet> userMap = new TreeMap<>();
        sheetList.parallelStream().forEachOrdered(element -> {
            if (userMap.get(element.getSumExamCertificateScore())
                    == null) {
                userMap.put(element.getSumExamCertificateScore(),
                        element);
            } else {
                if (element.getAverageSchoolCertificateScore() > userMap
                        .get(element.getSumExamCertificateScore())
                        .getAverageSchoolCertificateScore()) {
                    userMap.put(element.getSumExamCertificateScore(),
                            element);
                }
            }
        });

        //check attendees limit and delete others
        Iterator iterator = userMap.entrySet().iterator();
        int counter = 0;
        while (iterator.hasNext() && counter < userMap.size() - attendeesLimit) {
            iterator.remove();
            counter++;
        }
        return userMap.values().parallelStream().map(Sheet::getUser)
                .collect(Collectors.toList());
    }
}