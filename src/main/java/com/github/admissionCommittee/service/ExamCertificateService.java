package com.github.admissionCommittee.service;

import com.github.admissionCommittee.dao.DaoFactory;
import com.github.admissionCommittee.model.ExamCertificate;

public class ExamCertificateService extends GenericService<ExamCertificate> {

    public ExamCertificateService() {
        super(ExamCertificate.class, DaoFactory.getDaoFactory()
                .getExamCertificateDao());
    }

}

