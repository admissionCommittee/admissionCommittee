package com.github.admissionCommittee.util;

import com.github.admissionCommittee.model.Role;
import com.github.admissionCommittee.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Created by Admin on 07.11.2017.
 */
public class HibernateUtil {
    private static SessionFactory sessionFactory = null;

    static {
        Configuration configuration = new Configuration().configure
                ().addAnnotatedClass(User.class)
                .addAnnotatedClass(Role.class);
        StandardServiceRegistryBuilder builder
                = new StandardServiceRegistryBuilder().applySettings
                (configuration.getProperties());
        sessionFactory = configuration.buildSessionFactory(builder.build());
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}