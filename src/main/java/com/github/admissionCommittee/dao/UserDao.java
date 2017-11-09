package com.github.admissionCommittee.dao;

import com.github.admissionCommittee.core.User;

import java.util.List;


public class UserDao extends GenericDao<User> {

    public UserDao() {
        super(User.class);
    }

    //for DB search by mail implementation
    public User getByMail(String mail) {
        openSessionWithTransaction();
        List userByMail = getSession().createQuery("SELECT u " +
                "from User u where u.mail=:mail").setParameter(
                "mail", mail).list();
        closeSessionWithTransaction();
        return (User) userByMail.get(0);
    }
}
