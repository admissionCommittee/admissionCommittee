package com.github.admissionCommittee.dao;

import com.github.admissionCommittee.core.SchoolCertificate;
import org.hibernate.Session;

import java.util.List;

public class SchoolCertificateDao extends GenericDao<SchoolCertificate> {

    public SchoolCertificateDao() {
        super(SchoolCertificate.class);
    }

}
