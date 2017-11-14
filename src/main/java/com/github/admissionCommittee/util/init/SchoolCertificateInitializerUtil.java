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

import java.util.ArrayList;
import java.util.List;

public class SchoolCertificateInitializerUtil implements
        InitializerUtil {
    @Override
    public void init(int entitiesNumber, String outputFile,
                     String inputFile) {
        SchoolCertificateValidatorUtil validator = new
                SchoolCertificateValidatorUtil();
        List<SchoolCertificate> schoolCertificates = new ArrayList<>();
        List<User> userList = new UserService().getAll();
        List<Subject> subjectList = new SubjectService().getAll();
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
                });
        ServiceFactory.getServiceFactory().getSchoolCertificateService().save
                (schoolCertificates);
        validator.validateInit(schoolCertificates);
        //update users
        ServiceFactory.getServiceFactory().getUserService().save(userList);
        System.out.println("SCHOOL INIT DONE");
    }
}