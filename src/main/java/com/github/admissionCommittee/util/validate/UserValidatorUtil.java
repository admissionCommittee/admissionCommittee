package com.github.admissionCommittee.util.validate;

import com.github.admissionCommittee.model.AbstractEntity;
import com.github.admissionCommittee.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserValidatorUtil extends ValidatorUtil {
    private static final Logger log = LoggerFactory.getLogger
            (UserValidatorUtil.class);

    @Override
    public void validateEntity(AbstractEntity entityToValidate) {
        User toValidate = (User) entityToValidate;
        log.info(String.format("Validating entity %s", toValidate));
        if (ValidatorUtil.validateStringEmpty(toValidate
                .getUserAttendeeState()
                .toString(), ValidatorUtil.MESSAGE_IF_USER_ATTENDEE_STATE_EMPTY)
                && ValidatorUtil.validateStringEmpty(toValidate.getUserRole()
                .toString(), ValidatorUtil.MESSAGE_IF_USER_ROLE_EMPTY)
                && ValidatorUtil.validateStringEmpty(toValidate.getFirstName(),
                ValidatorUtil.MESSAGE_IF_NAME_EMPTY)
                && ValidatorUtil.validateStringEmpty(toValidate.getPatronymic(),
                ValidatorUtil.MESSAGE_IF_PATRONYMIC_EMPTY)
                && ValidatorUtil.validateStringEmpty(toValidate.getLastName(),
                ValidatorUtil.MESSAGE_IF_LAST_NAME_EMPTY)
                && ValidatorUtil.validateStringEmpty(toValidate.getMail(),
                ValidatorUtil.MESSAGE_IF_EMAIL_EMPTY)
                && ValidatorUtil.validateStringEmpty(toValidate.getPassword(),
                ValidatorUtil.MESSAGE_IF_PASSWORD_EMPTY)) {
            throw new IllegalStateException("User" + toValidate + "validation" +
                    " fails.");
        }
        //TODO other validation
    }
}