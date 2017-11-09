package com.github.admissionCommittee.service;

import com.github.admissionCommittee.core.Faculty;
import com.github.admissionCommittee.core.User;
import com.github.admissionCommittee.dao.UserDao;

import java.util.Map;

public class UserService extends GenericService<User> {

    public UserService(UserDao userDao) {
        super(User.class, userDao);
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