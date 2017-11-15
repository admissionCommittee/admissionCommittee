package com.github.admissionCommittee.util.validate;

import com.github.admissionCommittee.model.AbstractEntity;
import com.github.admissionCommittee.model.ExamCertificate;
import com.github.admissionCommittee.service.ServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ExamCertificateValidatorUtil extends ValidatorUtil {
    private static final Logger log = LoggerFactory.getLogger
            (ExamCertificateValidatorUtil.class);

    @Override
    public void validate(AbstractEntity entityToValidate) {
        ExamCertificate examCertificate = ((ExamCertificate) entityToValidate);
        ValidatorUtil.validateCertificateYear(examCertificate.getYear(), "Exam's "
                + "certificate year is invalid");
        ValidatorUtil.validateNotNull(examCertificate.getUser(), examCertificate.getSubjects(),
                "User assigned to exam certificate can't ve null",
                "Subject's collection assigned to exam certificate can't"
                        + " be null");
    }

    @Override
    public void validateInit(List<? extends AbstractEntity> toValidate) {
        List<? extends AbstractEntity> entitiesList = ServiceFactory
                .getServiceFactory().getService(toValidate.get(0).getClass())
                .getAll();
        //why id 1
        log.info(String.format("Check: from DB %s", entitiesList));
        log.info(String.format("Check: initial: %s", toValidate));
    }
}