package com.github.admissionCommittee.service;

import com.github.admissionCommittee.dao.DaoFactory;
import com.github.admissionCommittee.model.ExamCertificate;
import com.github.admissionCommittee.util.validate.ExamCertificateValidatorUtil;

import java.util.List;

public class ExamCertificateService extends GenericService<ExamCertificate> {

    public ExamCertificateService() {
        super(ExamCertificate.class, DaoFactory.getDaoFactory()
                .getExamCertificateDao());
    }

    @Override
    public List<String> save(ExamCertificate instance) {
        super.save(instance);
        List<String> errorsLog = new ExamCertificateValidatorUtil().validate
                (instance);
        return errorsLog;
    }
}