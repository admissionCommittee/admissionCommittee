package com.github.admissionCommittee.service;

import com.github.admissionCommittee.model.AbstractEntity;
import com.github.admissionCommittee.dao.GenericDao;
import com.github.admissionCommittee.util.Validator;

import java.util.List;

public abstract class GenericService<T extends AbstractEntity> {

    private Class<T> modelType;
    private GenericDao<T> dao;

    public GenericService(Class<T> modelType, GenericDao<T> dao) {
        this.modelType = modelType;
        this.dao = dao;
    }

    public void save(T instance) {
        Validator.validateNotNull(instance, Validator
                .MESSAGE_FOR_SOURCE_IF_NULL);
        if (instance.isNew()) {
            getDao().create(instance);
        }else{
            getDao().update(instance);
        }
    }

    public T get(long id) {
        Validator.validateNotNegative(id, Validator.MESSAGE_IF_NEGATIVE);
        return dao.get(id);
    }


    @SuppressWarnings("unchecked")
    public List<T> getAll() {
        return dao.getAll();
    }

    public void delete(long id) {
        Validator.validateNotNegative(id, Validator.MESSAGE_IF_NEGATIVE);
        dao.delete(id);
    }

    public GenericDao<T> getDao() {
        return dao;
    }

    public void setDao(GenericDao<T> dao) {
        Validator.validateNotNull(dao, Validator.MESSAGE_FOR_SOURCE_IF_NULL);
        this.dao = dao;
    }

    public Class<T> getModelType() {
        return modelType;
    }

    public void setModelType(Class<T> modelType) {
        Validator.validateNotNull(modelType, Validator
                .MESSAGE_FOR_SOURCE_IF_NULL);
        this.modelType = modelType;
    }
}