package com.github.admissionCommittee.service;

import com.github.admissionCommittee.core.ExamCertificate;
import com.github.admissionCommittee.core.Sheet;
import com.github.admissionCommittee.dao.ExamCertificateDao;
import com.github.admissionCommittee.dao.SheetDao;

public class ExamCertificateService extends GenericService<ExamCertificate> {

    public ExamCertificateService(ExamCertificateDao examCertificateDao) {
        super(ExamCertificate.class, examCertificateDao);
    }

    @Override
    public void save(ExamCertificate examCertificate) {
        super.save(examCertificate);

        //TODO calculate average and execute
        new SheetService(new SheetDao()).save(new Sheet());
    }
}

