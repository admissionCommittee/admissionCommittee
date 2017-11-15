package com.github.admissionCommittee.util.validate;

import com.github.admissionCommittee.model.AbstractEntity;
import com.github.admissionCommittee.model.Sheet;
import com.github.admissionCommittee.service.ServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class SheetValidatorUtil extends ValidatorUtil {
    private static final Logger log = LoggerFactory.getLogger
            (SheetValidatorUtil.class);

    @Override
    public void validate(AbstractEntity entityToValidate) {
        Sheet sheet = (Sheet) entityToValidate;
        ValidatorUtil.validateNotNull(sheet.getUser(), sheet.getFaculty(),
                "User assigned to sheet can't be null",
                "Faculty assigned to sheet can't be null");
//        ValidatorUtil.validateNotNegative(sheet.getSumExamCertificateScore(),
//                (long) sheet.getAverageSchoolCertificateScore(),
//                "Exam's score sum can't be negative",
//                "Average School's certificate score " +
//                        "can't be negative");
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