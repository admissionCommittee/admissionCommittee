package com.github.admissionCommittee.service;

import com.github.admissionCommittee.core.Faculty;
import com.github.admissionCommittee.core.User;
import com.github.admissionCommittee.dao.UserDao;
import com.github.admissionCommittee.util.Validator;

import java.util.Map;

public class UserService extends GenericService<User> {

    public UserService(UserDao userDao) {
        super(User.class, userDao);
    }

    @Override
    public void save(User user) {
        Validator.validateNotNull(user, Validator.MESSAGE_FOR_SOURCE_IF_NULL);
        if (getByMail(user.getMail()) == null) {
            getDao().save(user);
        }
    }

    //for DB search by mail implementation
    public User getByMail(String mail) {
        return ((UserDao) getDao()).getByMail(mail);
    }

    //get only attendees that satisfy the attendee demands
    public Map<Faculty, User> getAppropriate() {
        //TODO
        return null;
    }
}