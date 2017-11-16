package com.github.admissionCommittee.util.validate;

import com.github.admissionCommittee.model.AbstractEntity;
import com.github.admissionCommittee.model.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashSet;
import java.util.Set;

public class SheetValidatorUtil extends ValidatorUtil {
    private static final Logger log = LoggerFactory.getLogger
            (SheetValidatorUtil.class);

    @Override
    public Set<String> validate(AbstractEntity entityToValidate) {
        Set<String> errorsLog = new LinkedHashSet<>();
        Sheet sheet = (Sheet) entityToValidate;
        if (ValidatorUtil.validateNotNull(sheet.getUser(), sheet.getFaculty(),
                "User assigned to sheet can't be null",
                "Faculty assigned to sheet can't be null")) {
            return errorsLog;
        }
//        ValidatorUtil.validateNotNegative(sheet.getSumExamCertificateScore(),
//                (long) sheet.getAverageSchoolCertificateScore(),
//                "Exam's score sum can't be negative",
//                "Average School's certificate score " +
//                        "can't be negative");
        else {
            errorsLog.add("User and/or Faculty can't by null for " + sheet);
        }
        return errorsLog;
    }
}