package com.github.admissionCommittee.util.init;

import com.github.admissionCommittee.model.Faculty;
import com.github.admissionCommittee.model.Subject;
import com.github.admissionCommittee.model.User;
import com.github.admissionCommittee.service.ServiceFactory;
import com.github.admissionCommittee.service.SubjectService;
import com.github.admissionCommittee.util.validate.FacultyValidatorUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static com.github.admissionCommittee.model.enums.UserTypeEnum.ADMIN;

public class FacultyInitializerUtil implements InitializerUtil {
    @Override
    public void init(int entitiesNumber, String inputFile,
                     String outputFile){
        try {
            FacultyValidatorUtil validator = new
                    FacultyValidatorUtil();
            List<Faculty> facultyList = new ArrayList<>();
            Files.lines(Paths.get(inputFile))
                    .map(line -> line.split("\u200B"))
                    .forEach(substring -> {
                        Faculty faculty = new Faculty(substring[2], Integer
                                .parseInt(substring[3]), getRandomSubject(
                                3));
                        validator.validateEntity(faculty);
                        facultyList.add(faculty);
                    });
            ServiceFactory.getServiceFactory().getFacultyService().save
                    (facultyList);
            validator.validateInit(facultyList);
            System.out.println("FACULTY INIT DONE");
            assignFacultyToUserRandom(facultyList);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private Set<Subject> getRandomSubject(int subjectsNumber) {
        SubjectService subjectService = ServiceFactory.getServiceFactory()
                .getSubjectService();
        List<Subject> subjectList = subjectService.getAll();
        Set<Subject> subjectSet = new HashSet<>();
        for (int i = 0; i < subjectsNumber; i++) {
            Random random = new Random();
            int nextInt = random.nextInt(subjectList.size());
            subjectSet.add(subjectList.get(nextInt));
            subjectList.remove(nextInt);
        }
        return subjectSet;
    }

    private void assignFacultyToUserRandom(List<Faculty> facultyList) {
        List<User> userList = ServiceFactory.getServiceFactory()
                .getUserService()
                .getAll();
        userList.stream().filter(user -> user.getUserRole() != ADMIN)
                .forEach(user -> {
                    Random random = new Random();
                    user.setFaculty(facultyList.get(random.nextInt
                            (facultyList.size())));
                });
        ServiceFactory.getServiceFactory().getUserService().save(userList);
    }
}