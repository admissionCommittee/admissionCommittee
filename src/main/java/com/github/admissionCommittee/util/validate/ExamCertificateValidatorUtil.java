package com.github.admissionCommittee.util.validate;

import com.github.admissionCommittee.model.AbstractEntity;
import com.github.admissionCommittee.model.ExamCertificate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

public class ExamCertificateValidatorUtil extends ValidatorUtil {
    private static final Logger log = LoggerFactory.getLogger
            (ExamCertificateValidatorUtil.class);

    @Override
    public Set<String> validate(AbstractEntity entityToValidate) {
        Set<String> errorLog = new LinkedHashSet<>();
        ExamCertificate examCertificate = ((ExamCertificate) entityToValidate);
        if (!ValidatorUtil.validateCertificateYear(examCertificate.getYear(),
                "")) {
            errorLog.add(String.format("Exam's certificate year is invalid: should be between %d and %d!",
                    LocalDate.now().getYear() - 117, LocalDate.now().getYear()));
        }
        ValidatorUtil.validateNotNull(examCertificate.getUser(),
                examCertificate.getSubjects(),
                "User assigned to exam certificate " +
                        "can't ve null",
                "Subject's collection assigned to" +
                        " exam certificate can't" + " be null");

        examCertificate.getSubjects().values().forEach(v -> {
            if (v < 0 | v > 100) {
                errorLog.add("Subject's score must be between 0 and" +
                        " " +
                        "100 " + "including borders!");
            }
        });
        return errorLog;
    }
}