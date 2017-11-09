package com.github.admissionCommittee.service;

import com.github.admissionCommittee.model.User;
import com.github.admissionCommittee.dao.UserDao;

public class UserService extends GenericService<User> {

    public UserService(UserDao userDao) {
        super(User.class, userDao);
    }

    //for DB search by mail implementation
    public User getByMail(String mail) {
        return ((UserDao) getDao()).getByMail(mail);
    }


}