package com.github.admissionCommittee.dao;

import com.github.admissionCommittee.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public abstract class GenericDao<T> {

    private Class<T> type;
    private Session session;
    private Transaction transaction;

    public GenericDao(Class<T> type) {
        this.type = type;
    }

    public void create(T instance) {
        openSessionWithTransaction();
        session.save(instance);
        closeSessionWithTransaction();
    }

    public void update(T instance) {
        openSessionWithTransaction();
        session.update(instance);
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
        List<T> instances = (List<T>) session.createQuery("from " + type
                .getName()).list();
        closeSessionWithTransaction();
        return instances;
    }

    public void delete(long id) {
        T instance = get(id);
        if (instance != null) {
            openSessionWithTransaction();
            session.delete(instance);
            session.flush();
            closeSession();
        }
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