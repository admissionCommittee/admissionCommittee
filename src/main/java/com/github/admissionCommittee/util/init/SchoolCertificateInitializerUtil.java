package com.github.admissionCommittee.util.init;

import com.github.admissionCommittee.model.SchoolCertificate;
import com.github.admissionCommittee.model.Subject;
import com.github.admissionCommittee.model.User;
import com.github.admissionCommittee.service.SubjectService;
import com.github.admissionCommittee.service.UserService;
import com.github.admissionCommittee.util.validate.ValidatorUtil;

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

        List<SchoolCertificate> schoolCertificates = new ArrayList<>();
        List<User> userList = new UserService().getAll();
        List<Subject> subjectList = new SubjectService().getAll();
        IntStream.rangeClosed(1, entitiesNumber)
                .forEach(i -> {
                    User user = userList.get(i);
                    SchoolCertificate schoolCertificate = new
                            SchoolCertificate(user, user.getBirthDate()
                            .getYear(),
                            getRandomSchoolScores(subjectList));
                    ValidatorUtil.getValidator(SchoolCertificate.class)
                            .validateEntity(schoolCertificate);
                    schoolCertificates.add(schoolCertificate);
                });
        //TODO
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

     /* final SchoolCertificate schoolCertificateIvanov = new
                SchoolCertificate(
                userList.get(0),
                2017,
                new HashMap<Subject, Integer>() {{
                    put(subjectPhysics, 3);
                    put(subjectInformatics, 3);
                    put(subjectChemistry, 3);
                    put(subjectBiology, 4);
                    put(subjectEnglish, 4);
                    put(subjectHistory, 4);
                    put(subjectRussian, 4);
                    put(subjectLiterature, 4);
                    put(subjectAlgebra, 3);
                    put(subjectGeometry, 3);
                    put(subjectGeography, 3);
                }}
        );*/
}
