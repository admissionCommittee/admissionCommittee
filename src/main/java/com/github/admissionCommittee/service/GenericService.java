package com.github.admissionCommittee.service;

import com.github.admissionCommittee.dao.GenericDao;
import com.github.admissionCommittee.model.AbstractEntity;
import com.github.admissionCommittee.util.validate.ValidatorUtil;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public abstract class GenericService<T extends AbstractEntity> {

    private Class<T> modelType;
    private GenericDao<T> dao;

    public GenericService(Class<T> modelType, GenericDao<T> dao) {
        this.modelType = modelType;
        this.dao = dao;
    }

    public Set<String> save(T instance) {
        ValidatorUtil.validateNotNull(instance, ValidatorUtil
                .MESSAGE_FOR_SOURCE_IF_NULL);
        Set<String> errorsLog = ValidatorUtil.getValidator(modelType).validate
                (instance);
        if (errorsLog.size() == 0) {
            getDao().save(instance);
        }
        return errorsLog;
    }

    public Set<String> save(List<T> instance) {
        ValidatorUtil.validateNotNull(instance, ValidatorUtil
                .MESSAGE_FOR_SOURCE_IF_NULL);
        Set<String> errorsLog = new LinkedHashSet<>();
        instance.forEach(element -> errorsLog.addAll(ValidatorUtil.getValidator
                (modelType).validate(element)));
        if (errorsLog.size() == 0) {
            getDao().save(instance);
        }
        return errorsLog;
    }

    public T get(long id) {
        ValidatorUtil.validateNotNegative(id, ValidatorUtil
                .MESSAGE_IF_NEGATIVE);
        return dao.get(id);
    }


    @SuppressWarnings("unchecked")
    public List<T> getAll() {
        return dao.getAll();
    }

    public void delete(long id) {
        ValidatorUtil.validateNotNegative(id, ValidatorUtil
                .MESSAGE_IF_NEGATIVE);
        dao.delete(id);
    }

    public GenericDao<T> getDao() {
        return dao;
    }

    public void setDao(GenericDao<T> dao) {
        ValidatorUtil.validateNotNull(dao, ValidatorUtil
                .MESSAGE_FOR_SOURCE_IF_NULL);
        this.dao = dao;
    }

    public Class<T> getModelType() {
        return modelType;
    }

    public void setModelType(Class<T> modelType) {
        ValidatorUtil.validateNotNull(modelType, ValidatorUtil
                .MESSAGE_FOR_SOURCE_IF_NULL);
        this.modelType = modelType;
    }
}