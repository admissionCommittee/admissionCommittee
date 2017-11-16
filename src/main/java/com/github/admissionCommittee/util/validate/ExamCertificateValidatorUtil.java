package com.github.admissionCommittee.util.validate;

import com.github.admissionCommittee.model.AbstractEntity;
import com.github.admissionCommittee.model.ExamCertificate;
import com.github.admissionCommittee.service.ServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashSet;
import java.util.List;
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
            errorLog.add("Exam's certificate year is invalid!");
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

    @Override
    public void validateInit(List<? extends
            AbstractEntity> toValidate) {
        List<? extends AbstractEntity> entitiesList = ServiceFactory
                .getServiceFactory().getService(toValidate.get(0)
                        .getClass())
                .getAll();
        //why id 1
        log.info(String.format("Check: from DB %s", entitiesList));
        log.info(String.format("Check: initial: %s", toValidate));
    }
}