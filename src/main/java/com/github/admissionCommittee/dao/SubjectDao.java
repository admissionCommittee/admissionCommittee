package com.github.admissionCommittee.dao;

import com.github.admissionCommittee.core.Subject;
import org.hibernate.Session;

import java.util.List;

public class SubjectDao extends GenericDao<Subject> {

    public SubjectDao() {
        super(Subject.class);
    }

}
