package com.github.admissionCommittee.service;

import com.github.admissionCommittee.core.SchoolCertificate;
import com.github.admissionCommittee.core.Sheet;
import com.github.admissionCommittee.dao.SchoolCertificateDao;
import com.github.admissionCommittee.dao.SheetDao;
import com.github.admissionCommittee.util.Validator;

public class SchoolCertificateService extends
        GenericService<SchoolCertificate> {
    public SchoolCertificateService(SchoolCertificateDao schoolCertificateDao) {
        super(SchoolCertificate.class, schoolCertificateDao);
    }

    @Override
    public void save(SchoolCertificate schoolCertificate) {
        Validator.validateNotNull(schoolCertificate, Validator
                .MESSAGE_FOR_SOURCE_IF_NULL);
        //TODO save/update

        //TODO calculate average and execute
        new SheetService(new SheetDao()).save(new Sheet());
    }
}