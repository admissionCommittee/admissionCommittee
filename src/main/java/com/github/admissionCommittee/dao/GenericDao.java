package com.github.admissionCommittee.dao;

import com.github.admissionCommittee.model.AbstractEntity;
import com.github.admissionCommittee.util.init.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public abstract class GenericDao<T extends AbstractEntity> {

    private Class<T> type;
    private Session session;
    private Transaction transaction;
    private static final Logger log = LoggerFactory.getLogger(GenericDao
            .class);

    public GenericDao(Class<T> type) {
        this.type = type;
    }

    public void save(T instance) {
        openSessionWithTransaction();
        if (instance.isNew()) {
            session.save(instance);
        } else {
            session.update(instance);
        }
        closeSessionWithTransaction();
    }

    public void save(List<T> instance) {
        openSessionWithTransaction();
        instance.forEach(t -> {
            if (t.isNew()) {
                session.save(t);
                log.info(String.format("Saved %s", t));
            } else {
                session.update(t);
                log.info(String.format("Updated %s", t));
            }
        });
        closeSessionWithTransaction();
    }

    public T get(long id) {
        openSessionWithTransaction();
        T instance = session.get(type, id);
        closeSessionWithTransaction();
        return instance;
    }

    @SuppressWarnings("unchecked")
    public List<T> getAll() {
        openSessionWithTransaction();
        List<T> instances = (List<T>) session.createQuery("from "
                + type.getName()).list();
        closeSessionWithTransaction();
        return instances;
    }

    public void delete(long id) {
        openSessionWithTransaction();
        T instance = session.get(type, id);
        if (instance != null) {
            session.delete(instance);
            log.info(String.format("Deleted %s", instance));
        }
        closeSessionWithTransaction();
    }

    public Class<T> getType() {
        return type;
    }

    public Session getSession() {
        return session;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public Session openSessionWithTransaction() {
        session = openSession();
        transaction = session.beginTransaction();
        return session;
    }

    public Session openSession() {
        session = HibernateUtil.getSessionFactory().openSession();
        return session;
    }

    public void closeSessionWithTransaction() {
        transaction.commit();
        closeSession();
    }

    public void closeSession() {
        session.close();
    }

}