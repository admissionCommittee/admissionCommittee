package com.github.admissionCommittee.util.validate;

import com.github.admissionCommittee.model.AbstractEntity;
import com.github.admissionCommittee.model.User;

public class UserValidatorUtil extends ValidatorUtil {
    @Override
    public void validateEntity(AbstractEntity entityToValidate) {
        User toValidate = (User) entityToValidate;
        System.out.println(toValidate);
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

    /*@Override
    public void validateInit(List toValidate) {
        List<User> userList = new UserService().getAll();
        if (!(userList.containsAll(toValidate) && toValidate.containsAll
                (userList))) {
            throw new IllegalStateException(ValidatorUtil
                    .MESSAGE_IF_USERS_TABLE_INIT_FAIL);
        }
    }*/
}