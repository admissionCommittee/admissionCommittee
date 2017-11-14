package com.github.admissionCommittee.util.init;

import com.github.admissionCommittee.model.ExamCertificate;
import com.github.admissionCommittee.model.User;
import com.github.admissionCommittee.model.enums.UserTypeEnum;
import com.github.admissionCommittee.service.ServiceFactory;
import com.github.admissionCommittee.util.ScoresUtil;
import com.github.admissionCommittee.util.validate.ValidatorUtil;

import java.util.ArrayList;
import java.util.List;

public class ExamCertificateInitializerUtil implements
        EntityInitializerUtil<ExamCertificate> {


    @Override
    public List<ExamCertificate> initEntities(int entitiesNumber, String
            outputFile, String inputFile) {
        ArrayList<ExamCertificate> examCertificates = new ArrayList<>();
        List<User> userList = ServiceFactory.getServiceFactory()
                .getUserService().getAll();
        ValidatorUtil validator = ValidatorUtil.getValidator(ExamCertificate
                .class);
        userList.stream().filter(user -> user.getUserRole() != UserTypeEnum
                .ADMIN)
                .forEach(user -> {
                    System.out.println("1");
                    System.out.println(user
                            .getFaculty()
                            .getSubjects());
                    System.out.println("2");
                    ExamCertificate examCertificate = new ExamCertificate
                            (user, 17 + user.getBirthDate().getYear(),
                                    ScoresUtil.getRandomScores(new
                                                    ArrayList<>(user
                                                    .getFaculty()
                                                    .getSubjects()),
                                            0, 100));
                    validator.validateEntity(examCertificate);
                    examCertificates.add(examCertificate);
                    //assign exam certificate to user
                    user.setExamCertificate(examCertificate);
                });
        ServiceFactory.getServiceFactory().getExamCertificateService().save
                (examCertificates);
        validator.validateInit(examCertificates);
        //update users
        ServiceFactory.getServiceFactory().getUserService().save(userList);
        System.out.println("EXAM INIT DONE");
        return examCertificates;
    }


}