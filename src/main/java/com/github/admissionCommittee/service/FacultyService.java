package com.github.admissionCommittee.service;

import com.github.admissionCommittee.dao.DaoFactory;
import com.github.admissionCommittee.model.Faculty;

public class FacultyService extends GenericService<Faculty> {

    public FacultyService() {
        super(Faculty.class, DaoFactory.getDaoFactory().getFacultyDao());
    }

}
