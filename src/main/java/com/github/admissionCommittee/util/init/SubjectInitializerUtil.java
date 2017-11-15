package com.github.admissionCommittee.util.init;

import com.github.admissionCommittee.model.Subject;
import com.github.admissionCommittee.model.enums.SubjectNameEnum;
import com.github.admissionCommittee.service.ServiceFactory;
import com.github.admissionCommittee.util.validate.SubjectValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubjectInitializerUtil implements InitializerUtil {
    private static final Logger log = LoggerFactory.getLogger
            (SubjectInitializerUtil.class);

    @Override
    public void init(int entitiesNumber, String outputFile,
                     String inputFile) {
        List<Subject> subjectList = new ArrayList<>();
        SubjectValidatorUtil validator = new SubjectValidatorUtil();
        final int[] counter = {0};
        Arrays.stream(SubjectNameEnum.values())
                .forEach(value -> {
                    Subject subject = new Subject(value);
                    validator.validate(subject);
                    subjectList.add(subject);
                    counter[0]++;
                });
        ServiceFactory.getServiceFactory().getSubjectService().save
                (subjectList);
        validator.validateInit(subjectList);
        log.info(String.format("Subjects have been initialized successfully," +
                " total %d subjects", counter[0]));
    }
}