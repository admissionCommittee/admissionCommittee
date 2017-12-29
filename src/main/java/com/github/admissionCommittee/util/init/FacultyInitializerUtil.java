package com.github.admissionCommittee.util.init;

import com.github.admissionCommittee.model.Faculty;
import com.github.admissionCommittee.model.Subject;
import com.github.admissionCommittee.model.User;
import com.github.admissionCommittee.model.enums.SubjectNameEnum;
import com.github.admissionCommittee.service.ServiceFactory;
import com.github.admissionCommittee.service.SubjectService;
import com.github.admissionCommittee.util.validate.FacultyValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static com.github.admissionCommittee.model.enums.SubjectNameEnum.INFORMATICS;
import static com.github.admissionCommittee.model.enums.SubjectNameEnum.RUSSIAN;
import static com.github.admissionCommittee.model.enums.UserTypeEnum.ADMIN;

public class FacultyInitializerUtil implements InitializerUtil {
    private static final Logger log = LoggerFactory.getLogger
            (FacultyInitializerUtil.class);

    @Override
    public Set<String> init(int entitiesNumber, String inputFile,
                            String outputFile) {
        Set<String> errorsLog = new LinkedHashSet<>();
        try {
            FacultyValidatorUtil validator = new
                    FacultyValidatorUtil();
            List<Faculty> facultyList = new ArrayList<>();
            final int[] counter = {0};
            Files.lines(Paths.get(inputFile))
                    .map(line -> line.split("\u200B"))
                    .forEach(substring -> {
                        Faculty faculty = new Faculty(substring[2], Integer
                                .parseInt(substring[3]), getRandomSubject(
                                1));
                        errorsLog.addAll(validator.validate(faculty));
                        facultyList.add(faculty);
                        counter[0]++;
                    });
            ServiceFactory.getFacultyService().save
                    (facultyList);
            errorsLog.addAll(validator.validateInit(facultyList));
            log.info(String.format("Faculties have been initialized" +
                    "successfully, total %d faculties", counter[0]));
            assignFacultyToUserRandom(facultyList);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return errorsLog;
    }

    private Set<Subject> getRandomSubject(int randomSubjectsNumber) {
        SubjectService subjectService = ServiceFactory.getSubjectService();
        List<Subject> subjectList = subjectService.getAll();
        Set<Subject> subjectSet = new HashSet<>();
        Random random = new Random();
        int nextInt = 0;
        Subject randomSubject = null;
        for (int i = 0; i < randomSubjectsNumber; i++) {
            do {
                nextInt = random.nextInt(subjectList.size());
                randomSubject = subjectList.get(nextInt);
            } while (randomSubject.getName() == INFORMATICS | randomSubject.getName() == RUSSIAN);
            subjectSet.add(randomSubject);
            subjectList.remove(randomSubject);
        }
        subjectSet.add(ServiceFactory.getSubjectService().get(1));
        subjectSet.add(ServiceFactory.getSubjectService().get(10));
        return subjectSet;
    }

    private void assignFacultyToUserRandom(List<Faculty> facultyList) {
        List<User> userList = ServiceFactory.getUserService()
                .getAll();
        userList.stream().filter(user -> user.getUserRole() != ADMIN)
                .forEach(user -> {
                    Random random = new Random();
                    user.setFaculty(facultyList.get(random.nextInt
                            (facultyList.size())));
                });
        ServiceFactory.getUserService().save(userList);
    }
}