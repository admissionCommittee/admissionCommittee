package com.github.admissionCommittee.util.validate;

import com.github.admissionCommittee.model.AbstractEntity;
import com.github.admissionCommittee.model.SchoolCertificate;
import com.github.admissionCommittee.service.ServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class SchoolCertificateValidatorUtil extends ValidatorUtil {
    private static final Logger log = LoggerFactory.getLogger
            (SchoolCertificateValidatorUtil.class);

    @Override
    public List<String> validate(AbstractEntity entityToValidate) {
        List<String> errorLog = new ArrayList<>();
        SchoolCertificate schoolCertificate = (SchoolCertificate)
                entityToValidate;
        ValidatorUtil.validateNotNull(schoolCertificate.getUser(),
                schoolCertificate.getSubjects(),
                "User assigned to schoolCertificate "
                        + "can't be null",
                "Subject's map assigned to school" +
                        "Certificate can't be null");
        if (!ValidatorUtil.validateCertificateYear(schoolCertificate.getYear(),
                "")) {
            errorLog.add("School's certificate year is invalid!");
        }
        schoolCertificate.getSubjects().values().forEach(v -> {
            if (v < 0 | v > 5) {
                errorLog.add("Subject's score valid interval is between 0 and" +
                        " 5 including borders!");
            }
        });
        return errorLog;
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