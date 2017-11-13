package com.github.admissionCommittee.service;

import com.github.admissionCommittee.dao.DaoFactory;
import com.github.admissionCommittee.model.Subject;

public class SubjectService extends GenericService<Subject> {
    public SubjectService() {
        super(Subject.class, DaoFactory.getDaoFactory().getSubjectDao());
    }
}