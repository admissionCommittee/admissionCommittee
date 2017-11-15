package com.github.admissionCommittee.util.init;

import com.github.admissionCommittee.model.SchoolCertificate;
import com.github.admissionCommittee.model.Subject;
import com.github.admissionCommittee.model.User;
import com.github.admissionCommittee.model.enums.UserTypeEnum;
import com.github.admissionCommittee.service.ServiceFactory;
import com.github.admissionCommittee.service.SubjectService;
import com.github.admissionCommittee.service.UserService;
import com.github.admissionCommittee.util.ScoresUtil;
import com.github.admissionCommittee.util.validate
        .SchoolCertificateValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class SchoolCertificateInitializerUtil implements
        InitializerUtil {
    private static final Logger log = LoggerFactory.getLogger
            (SchoolCertificateInitializerUtil.class);

    @Override
    public void init(int entitiesNumber, String outputFile,
                     String inputFile) {
        SchoolCertificateValidatorUtil validator = new
                SchoolCertificateValidatorUtil();
        List<SchoolCertificate> schoolCertificates = new ArrayList<>();
        List<User> userList = new UserService().getAll();
        List<Subject> subjectList = new SubjectService().getAll();
        final int[] counter = {0};
        userList.stream().filter(user -> user.getUserRole() != UserTypeEnum
                .ADMIN)
                .forEach(user -> {
                    SchoolCertificate schoolCertificate = new
                            SchoolCertificate(user, user.getBirthDate()
                            .getYear() + 17,
                            ScoresUtil.getRandomScores(subjectList, 3, 2));
                    validator.validateEntity(schoolCertificate);
                    schoolCertificates.add(schoolCertificate);
                    //assign school certificate to user
                    user.setSchoolCertificate(schoolCertificate);
                    counter[0]++;
                });
        ServiceFactory.getServiceFactory().getSchoolCertificateService().save
                (schoolCertificates);
        validator.validateInit(schoolCertificates);
        //update users
        ServiceFactory.getServiceFactory().getUserService().save(userList);
        log.info(String.format("School certificates have been initialized " +
                "successfully total %d school certificates", counter[0]));
    }
}