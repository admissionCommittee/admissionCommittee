package com.github.admissionCommittee.util.validate;

import com.github.admissionCommittee.model.AbstractEntity;
import com.github.admissionCommittee.model.User;
import com.github.admissionCommittee.util.validate.org.apache.commons
        .validator.routines.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.regex.Pattern;


public class UserValidatorUtil extends ValidatorUtil {
    private static final Logger log = LoggerFactory.getLogger
            (UserValidatorUtil.class);

    @Override
    public void validate(AbstractEntity entityToValidate) {
        User toValidate = (User) entityToValidate;
        log.info(String.format("Validating entity %s", toValidate));
        if (ValidatorUtil.validateStringEmpty(toValidate
                .getUserAttendeeState()
                .toString(), ValidatorUtil.MESSAGE_IF_USER_ATTENDEE_STATE_EMPTY)
                || ValidatorUtil.validateStringEmpty(toValidate.getUserRole()
                .toString(), ValidatorUtil.MESSAGE_IF_USER_ROLE_EMPTY)
                || ValidatorUtil.validateStringEmpty(toValidate.getFirstName(),
                ValidatorUtil.MESSAGE_IF_NAME_EMPTY)
                || ValidatorUtil.validateStringEmpty(toValidate.getPatronymic(),
                ValidatorUtil.MESSAGE_IF_PATRONYMIC_EMPTY)
                || ValidatorUtil.validateStringEmpty(toValidate.getLastName(),
                ValidatorUtil.MESSAGE_IF_LAST_NAME_EMPTY)
                || ValidatorUtil.validateStringEmpty(toValidate.getMail(),
                ValidatorUtil.MESSAGE_IF_EMAIL_EMPTY)
                || ValidatorUtil.validateStringEmpty(toValidate.getPassword(),
                ValidatorUtil.MESSAGE_IF_PASSWORD_EMPTY)
                || !validateEmail(toValidate.getMail())
                || !ValidatorUtil.validateFullName(toValidate.getFirstName() + toValidate
                .getLastName() + toValidate.getPatronymic(),"Full name")
                || !validatePassword(toValidate)
                || !validateBirthDay(toValidate.getBirthDate())) {
            throw new IllegalStateException("User" + toValidate + "validation" +
                    " fails.");
        }
    }

    private boolean validateEmail(String email) {
        boolean valid = EmailValidator.getInstance().isValid(email);
        if (!valid) {
            log.debug(String.format("Email %s is not valid.", email), email);
        }
        return valid;
    }

    private boolean validatePassword(User user) {
        PasswordValidatorUtil passwordValidatorUtil = new
                PasswordValidatorUtil();
        passwordValidatorUtil.validate(user);
        return passwordValidatorUtil.isValid;
    }

    private boolean validateBirthDay(LocalDate birthday) {
        if (birthday.getYear() < 1895 || birthday.getYear() > 2012) {
            return false;
        }
        return true;
    }
}