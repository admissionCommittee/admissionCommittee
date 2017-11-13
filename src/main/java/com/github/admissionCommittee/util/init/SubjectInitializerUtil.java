package com.github.admissionCommittee.util.init;

import com.github.admissionCommittee.model.Subject;
import com.github.admissionCommittee.model.enums.SubjectNameEnum;
import com.github.admissionCommittee.util.validate.ValidatorUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubjectInitializerUtil implements EntityInitializerUtil<Subject> {
    @Override
    public List<Subject> initEntities(int entitiesNumber, String outputFile,
                                      String inputFile) {
        List<Subject> subjectList = new ArrayList<>();

        Arrays.stream(SubjectNameEnum.values())
                .forEach(value -> {
                    Subject subject = new Subject(value);
                    ValidatorUtil.getValidator(Subject.class)
                            .validateEntity(subject);
                    subjectList.add(subject);
                });
        return subjectList;
    }
}