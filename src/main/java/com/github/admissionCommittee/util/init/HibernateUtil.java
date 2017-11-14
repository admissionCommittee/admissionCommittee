package com.github.admissionCommittee.util.init;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory;

    static {
        try {
            Configuration configuration = new Configuration().configure();
            sessionFactory = configuration.buildSessionFactory();
            DBInitializerUtil.initDatabase();
        } catch (Throwable t) {
            throw new ExceptionInInitializerError(t);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}