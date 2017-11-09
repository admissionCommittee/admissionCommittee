package com.github.admissionCommittee.dao;

import com.github.admissionCommittee.core.User;


public class UserDao extends GenericDao<User> {

    public UserDao() {
        super(User.class);
    }

    //for DB search by mail implementation
    public User getByMail(String mail) {
        openSessionWithTransaction();
        final User result = (User) getSession()
            .createQuery("from User u where u.mail=:mail")
            .setParameter("mail", mail)
            .uniqueResult();
        closeSessionWithTransaction();
        return result;
    }

}
