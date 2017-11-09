package com.github.admissionCommittee.dao;

import com.github.admissionCommittee.core.User;

import java.util.List;


public class UserDao extends GenericDao<User> {

    public UserDao() {
        super(User.class);
    }

    //for DB search by mail implementation
    public User getByName(String mail) {
        openSessionWithTransaction();
        //TODO
        closeSessionWithTransaction();
        return null;
    }

}
