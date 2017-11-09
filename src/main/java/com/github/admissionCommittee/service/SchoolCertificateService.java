package com.github.admissionCommittee.service;

import com.github.admissionCommittee.model.SchoolCertificate;
import com.github.admissionCommittee.model.Sheet;
import com.github.admissionCommittee.dao.SchoolCertificateDao;
import com.github.admissionCommittee.dao.SheetDao;

public class SchoolCertificateService extends
        GenericService<SchoolCertificate> {
    public SchoolCertificateService(SchoolCertificateDao schoolCertificateDao) {
        super(SchoolCertificate.class, schoolCertificateDao);
    }

    @Override
    public void save(SchoolCertificate schoolCertificate) {
        super.save(schoolCertificate);

        //TODO calculate average and execute
        new SheetService(new SheetDao()).save(new Sheet());
    }
}