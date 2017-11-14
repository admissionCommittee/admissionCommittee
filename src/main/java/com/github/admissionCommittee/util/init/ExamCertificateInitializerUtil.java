package com.github.admissionCommittee.util.init;

import com.github.admissionCommittee.model.ExamCertificate;
import com.github.admissionCommittee.model.Subject;
import com.github.admissionCommittee.model.User;
import com.github.admissionCommittee.service.UserService;
import com.github.admissionCommittee.util.validate.ValidatorUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class ExamCertificateInitializerUtil implements
        EntityInitializerUtil<ExamCertificate> {

    @Override
    public List<ExamCertificate> initEntities(int entitiesNumber, String
            outputFile, String inputFile) {
        ArrayList<ExamCertificate> examCertificates = new ArrayList<>();
        List<User> userList = new UserService().getAll();
        userList.forEach(user -> {
            ExamCertificate examCertificate = new ExamCertificate
                    (user, 17 + user.getBirthDate().getYear(),
                            getRandomExamScores(new ArrayList<>(user
                                    .getFaculty()
                                    .getSubjects())));
            ValidatorUtil.getValidator(ExamCertificate.class)
                    .validateEntity(examCertificate);
            examCertificates.add(examCertificate);
        });
        return examCertificates;
    }

    private HashMap<Subject, Integer> getRandomExamScores(
            List<Subject> subjectList) {
        HashMap<Subject, Integer> randomScores = new HashMap<>();
        Random random = new Random();
        subjectList.forEach(subject -> {
                    randomScores.put(subject, random.nextInt(101));
                }
        );
        return randomScores;
    }
}