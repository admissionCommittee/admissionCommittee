package com.github.admissionCommittee.service;

import com.github.admissionCommittee.dao.DaoFactory;
import com.github.admissionCommittee.model.SchoolCertificate;
import com.github.admissionCommittee.model.Subject;
import com.github.admissionCommittee.util.validate
        .SchoolCertificateValidatorUtil;

import java.util.Map;
import java.util.Set;

public class SchoolCertificateService extends
        GenericService<SchoolCertificate> {

    public SchoolCertificateService() {
        super(SchoolCertificate.class, DaoFactory.getDaoFactory()
                .getSchoolCertificateDao());
    }

    @Override
    public Set<String> save(SchoolCertificate instance) {
        instance.setAverageScore(calculateAverageScore(instance));
        Set<String> errorsLog = new SchoolCertificateValidatorUtil().validate
                (instance);
        if (errorsLog.size() == 0) {
            super.save(instance);
        }
        return errorsLog;
    }

    private double calculateAverageScore(SchoolCertificate instance) {
        final Map<Subject, Integer> schoolScoreMap = instance.getSubjects();
        final Integer schoolScoreSum = schoolScoreMap.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
        // return average rounded by 2 decimal places
        return Math.round(100.0 * schoolScoreSum / schoolScoreMap.size()) /
                100.0;
    }

}