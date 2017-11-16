package com.github.admissionCommittee.service;

import com.github.admissionCommittee.dao.DaoFactory;
import com.github.admissionCommittee.model.ExamCertificate;
import com.github.admissionCommittee.util.validate.ExamCertificateValidatorUtil;

import java.util.Set;

public class ExamCertificateService extends GenericService<ExamCertificate> {

    public ExamCertificateService() {
        super(ExamCertificate.class, DaoFactory.getDaoFactory()
                .getExamCertificateDao());
    }

    @Override
    public Set<String> save(ExamCertificate instance) {

        Set<String> errorsLog = new ExamCertificateValidatorUtil().validate
                (instance);
        if (errorsLog.size() == 0) {
            super.save(instance);
        }
        return errorsLog;
    }
}