package com.github.admissionCommittee.service;

import com.github.admissionCommittee.dao.DaoFactory;
import com.github.admissionCommittee.model.Faculty;
import com.github.admissionCommittee.dao.FacultyDao;

public class FacultyService extends GenericService<Faculty> {

    public FacultyService() {
        super(Faculty.class, DaoFactory.getFacultyDao());
    }
}
