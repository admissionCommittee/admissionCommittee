package com.github.admissionCommittee.util.init;

import com.github.admissionCommittee.model.ExamCertificate;
import com.github.admissionCommittee.model.User;
import com.github.admissionCommittee.model.enums.UserTypeEnum;
import com.github.admissionCommittee.service.ServiceFactory;
import com.github.admissionCommittee.util.ScoresUtil;
import com.github.admissionCommittee.util.validate.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ExamCertificateInitializerUtil implements
        InitializerUtil {
    private static final Logger log = LoggerFactory.getLogger
            (ExamCertificateInitializerUtil.class);

    @Override
    public void init(int entitiesNumber, String outputFile,
                     String inputFile) {
        ArrayList<ExamCertificate> examCertificates = new ArrayList<>();
        List<User> userList = ServiceFactory.getServiceFactory()
                .getUserService().getAll();
        ValidatorUtil validator = ValidatorUtil.getValidator(ExamCertificate
                .class);
        final int[] counter = {0};
        userList.stream().filter(user -> user.getUserRole() != UserTypeEnum
                .ADMIN)
                .forEach(user -> {
                    ExamCertificate examCertificate = new ExamCertificate
                            (user, 17 + user.getBirthDate().getYear(),
                                    ScoresUtil.getRandomScores(new
                                                    ArrayList<>(user
                                                    .getFaculty()
                                                    .getSubjects()),
                                            0, 100));
                    validator.validate(examCertificate);
                    examCertificates.add(examCertificate);
                    //assign exam certificate to user
                    user.setExamCertificate(examCertificate);
                    counter[0]++;
                });
        ServiceFactory.getServiceFactory().getExamCertificateService().save
                (examCertificates);
        validator.validateInit(examCertificates);
        //update users
        ServiceFactory.getServiceFactory().getUserService().save(userList);
        log.info(String.format("Exam certificates have been initialized" +
                "successfully, total %d exam certificates", counter[0]));
    }
}