package com.github.admissionCommittee.util.init;

import com.github.admissionCommittee.model.Subject;
import com.github.admissionCommittee.model.enums.SubjectNameEnum;
import com.github.admissionCommittee.service.ServiceFactory;
import com.github.admissionCommittee.util.validate.SubjectValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class SubjectInitializerUtil implements InitializerUtil {
    private static final Logger log = LoggerFactory.getLogger
            (SubjectInitializerUtil.class);

    @Override
    public Set<String> init(int entitiesNumber, String outputFile,
                            String inputFile) {
        Set<String> errorsLog = new LinkedHashSet<>();
        List<Subject> subjectList = new ArrayList<>();
        SubjectValidatorUtil validator = new SubjectValidatorUtil();
        final int[] counter = {0};
        Arrays.stream(SubjectNameEnum.values())
                .forEach(value -> {
                    Subject subject = new Subject(value);
                    errorsLog.addAll(validator.validate(subject));
                    subjectList.add(subject);
                    counter[0]++;
                });
        ServiceFactory.getSubjectService().save
                (subjectList);
        errorsLog.addAll(validator.validateInit(subjectList));
        log.info(String.format("Subjects have been initialized successfully," +
                " total %d subjects", counter[0]));
        return errorsLog;
    }
}