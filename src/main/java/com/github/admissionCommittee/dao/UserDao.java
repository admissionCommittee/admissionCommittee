package com.github.admissionCommittee.dao;

import com.github.admissionCommittee.core.User;
import org.hibernate.Session;

import java.util.List;

public class UserDao extends GenericDao<User> {

    public UserDao() {
        super(User.class);
    }

}
