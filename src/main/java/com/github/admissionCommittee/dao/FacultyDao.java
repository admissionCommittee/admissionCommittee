package com.github.admissionCommittee.dao;

import com.github.admissionCommittee.core.Faculty;
import org.hibernate.Session;

import java.util.List;

public class FacultyDao extends GenericDao<Faculty> {

    public FacultyDao() {
        super(Faculty.class);
    }

}
