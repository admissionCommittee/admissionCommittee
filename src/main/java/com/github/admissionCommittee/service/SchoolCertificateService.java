package com.github.admissionCommittee.service;

import com.github.admissionCommittee.dao.DaoFactory;
import com.github.admissionCommittee.model.SchoolCertificate;

public class SchoolCertificateService extends GenericService<SchoolCertificate> {

    public SchoolCertificateService() {
        super(SchoolCertificate.class, DaoFactory.getDaoFactory().getSchoolCertificateDao());
    }

}