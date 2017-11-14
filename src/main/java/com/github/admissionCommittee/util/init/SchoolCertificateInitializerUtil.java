package com.github.admissionCommittee.util.init;

import com.github.admissionCommittee.model.SchoolCertificate;
import com.github.admissionCommittee.model.Subject;
import com.github.admissionCommittee.model.User;
import com.github.admissionCommittee.service.ServiceFactory;
import com.github.admissionCommittee.service.SubjectService;
import com.github.admissionCommittee.service.UserService;
import com.github.admissionCommittee.util.validate
        .SchoolCertificateValidatorUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class SchoolCertificateInitializerUtil implements
        EntityInitializerUtil<SchoolCertificate> {
    @Override
    public List<SchoolCertificate> initEntities(int entitiesNumber, String
            inputFile, String outputFile) {
        SchoolCertificateValidatorUtil validator = new
                SchoolCertificateValidatorUtil();
        List<SchoolCertificate> schoolCertificates = new ArrayList<>();
        List<User> userList = new UserService().getAll();
        List<Subject> subjectList = new SubjectService().getAll();
        IntStream.rangeClosed(1, entitiesNumber)
                .forEach(i -> {
                    User user = userList.get(i);
                    SchoolCertificate schoolCertificate = new
                            SchoolCertificate(user, user.getBirthDate()
                            .getYear() + 17,
                            getRandomSchoolScores(subjectList));
                    validator.validateEntity(schoolCertificate);
                    schoolCertificates.add(schoolCertificate);
                    //assign school certificate to user
                    user.setSchoolCertificate(schoolCertificate);
                });
        ServiceFactory.getServiceFactory().getSchoolCertificateService().save
                (schoolCertificates);
        validator.validateInit(schoolCertificates);
        ServiceFactory.getServiceFactory().getUserService().save(userList);
        System.out.println("SCHOOL INIT DONE");
        return schoolCertificates;
    }

    private HashMap<Subject, Integer> getRandomSchoolScores(
            List<Subject> subjectList) {
        HashMap<Subject, Integer> randomScores = new HashMap<>();
        Random random = new Random();
        subjectList.forEach(subject -> {
                    randomScores.put(subject, 3 + random.nextInt(3));
                }
        );
        return randomScores;
    }
}