package com.github.admissionCommittee.dao;

import com.github.admissionCommittee.model.Faculty;

public class FacultyDao extends GenericDao<Faculty> {

    protected FacultyDao() {
        super(Faculty.class);
    }
}
