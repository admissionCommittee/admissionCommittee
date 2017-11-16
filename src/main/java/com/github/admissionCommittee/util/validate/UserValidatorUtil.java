package com.github.admissionCommittee.util.validate;

import com.github.admissionCommittee.model.AbstractEntity;
import com.github.admissionCommittee.model.User;
import com.github.admissionCommittee.util.validate.org.apache.commons
        .validator.routines.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class UserValidatorUtil extends ValidatorUtil {
    private static final Logger log = LoggerFactory.getLogger
            (UserValidatorUtil.class);


    @Override
    public List<String> validate(AbstractEntity entityToValidate) {
        User toValidate = (User) entityToValidate;
        log.info(String.format("Validating entity %s", toValidate));
        List<String> errorsList = new ArrayList<>();
        if (ValidatorUtil.validateStringEmpty(toValidate
                .getUserAttendeeState().toString(), ValidatorUtil
                .MESSAGE_IF_USER_ATTENDEE_STATE_EMPTY)) {
            errorsList.add("User's state initialization error, please, " +
                    "contact with " +
                    "the site administrator!");
        }
        if (ValidatorUtil.validateStringEmpty(toValidate.getUserRole()
                .toString(), ValidatorUtil.MESSAGE_IF_USER_ROLE_EMPTY)) {
            errorsList.add("User's role initialization error, please, " +
                    "contact with " +
                    "the site administrator!");
        }
        if (ValidatorUtil.validateStringEmpty(toValidate.getFirstName(),
                ValidatorUtil.MESSAGE_IF_NAME_EMPTY)) {
            errorsList.add("First name is required!");
        }
        if (ValidatorUtil.validateStringEmpty(toValidate
                        .getPatronymic(),
                ValidatorUtil.MESSAGE_IF_PATRONYMIC_EMPTY)) {
            errorsList.add("Patronymic is required!");
        }
        if (ValidatorUtil.validateStringEmpty(toValidate.getLastName(),
                ValidatorUtil.MESSAGE_IF_LAST_NAME_EMPTY)) {
            errorsList.add("Last name is required!");
        }
        if (ValidatorUtil.validateStringEmpty(toValidate.getMail(),
                ValidatorUtil.MESSAGE_IF_EMAIL_EMPTY)) {
            errorsList.add("Email is required!");
        }
        if (ValidatorUtil.validateStringEmpty(toValidate.getPassword(),
                ValidatorUtil.MESSAGE_IF_PASSWORD_EMPTY)) {
            errorsList.add("Password is required!");
        }
        if (!validateEmail(toValidate.getMail())) {
            errorsList.add("Email is not valid!");
        }
        if (!ValidatorUtil.validateFullName(toValidate.getFirstName()
                        + toValidate.getLastName() + toValidate.getPatronymic(),
                "Full" + "name")) {
            errorsList.add("Full name is not valid!");
        }

        if (!validateBirthDay(toValidate.getBirthDate())) {
            errorsList.add("Birthday's year for registration attendee should " +
                    "is valid when belween 1895 and 2012!");
        }
        errorsList.addAll(validatePassword(toValidate));
        return errorsList;
    }

    private boolean validateEmail(String email) {
        boolean valid = EmailValidator.getInstance().isValid(email);
        if (!valid) {
            log.debug(String.format("Email %s is not valid.", email), email);
        }
        return valid;
    }

    private List<String> validatePassword(User user) {
        List<String> errorLog = PasswordValidatorUtil.getBuilder().build()
                .validate(user);
        return errorLog;
    }

    private boolean validateBirthDay(LocalDate birthday) {
        if (birthday.getYear() < 1895 || birthday.getYear() > 2012) {
            return false;
        }
        return true;
    }
}