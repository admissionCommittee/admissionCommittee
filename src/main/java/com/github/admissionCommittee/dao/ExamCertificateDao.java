package com.github.admissionCommittee.dao;

import com.github.admissionCommittee.core.ExamCertificate;
import org.hibernate.Session;

import java.util.List;

public class ExamCertificateDao extends GenericDao<ExamCertificate> {

    public ExamCertificateDao() {
        super(ExamCertificate.class);
    }

}
