package com.github.admissionCommittee.service;

import com.github.admissionCommittee.core.User;
import javassist.NotFoundException;

import java.util.List;

public interface UserService {

    void save(User user);

    User get(int id) throws NotFoundException;

    List<User> getAll();

    List<User> getAppropriate();

    void delete(int id) throws NotFoundException;

    void update(User user);
}
