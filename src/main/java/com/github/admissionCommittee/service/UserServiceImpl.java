package com.github.admissionCommittee.service;

import com.github.admissionCommittee.core.User;
import com.github.admissionCommittee.dao.UserDao;
import com.github.admissionCommittee.util.Validator;
import javassist.NotFoundException;

import java.util.List;

public class UserServiceImpl implements UserService {
    //TODO exception impl
    private final UserDao repository;

    public UserServiceImpl(UserDao repository) {
        this.repository = repository;
    }

    @Override
    public void save(User user) {
        Validator.validateNotNull(user, Validator.MESSAGE_FOR_SOURCE_IF_NULL);
        if(getByMail(user.getMail())==null){
        repository.save(user);}
    }

    @Override
    public User get(int id) throws NotFoundException {
        Validator.validateNotNegative(id, Validator.MESSAGE_IF_NEGATIVE);
        return repository.get(id);
    }

    @Override
    public List<User> getAll() {
        return repository.getAll();
    }

    //for DB search by mail implementation
    @Override
    public User getByMail(String mail) {
        return repository.getByName(mail);
    }

    @Override
    public List<User> getAppropriate() {
        //TODO
        return null;
    }

    @Override
    public void update(User user) {
        Validator.validateNotNull(user, Validator.MESSAGE_FOR_SOURCE_IF_NULL);
        repository.update(user);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        Validator.validateNotNegative(id, Validator.MESSAGE_IF_NEGATIVE);
        repository.delete(id);
    }

}