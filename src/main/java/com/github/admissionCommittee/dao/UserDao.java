package com.github.admissionCommittee.dao;

import com.github.admissionCommittee.model.User;

public class UserDao extends GenericDao<User> {

    protected UserDao() {
        super(User.class);
    }

    //for DB search by email implementation
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
