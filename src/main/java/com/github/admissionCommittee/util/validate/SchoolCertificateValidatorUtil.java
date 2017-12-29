package com.github.admissionCommittee.util.validate;

import com.github.admissionCommittee.model.AbstractEntity;
import com.github.admissionCommittee.model.SchoolCertificate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashSet;
import java.util.Set;

public class SchoolCertificateValidatorUtil extends ValidatorUtil {
    private static final Logger log = LoggerFactory.getLogger
            (SchoolCertificateValidatorUtil.class);

    @Override
    public Set<String> validate(AbstractEntity entityToValidate) {
        Set<String> errorLog = new LinkedHashSet<>();
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
                errorLog.add("Subject's score must be is between 0 and" +
                        " 5 including borders!");
            }
        });
        return errorLog;
    }
}