package com.github.admissionCommittee.service;

import com.github.admissionCommittee.dao.DaoFactory;
import com.github.admissionCommittee.model.SchoolCertificate;
import com.github.admissionCommittee.model.Subject;

import java.util.Map;

public class SchoolCertificateService extends GenericService<SchoolCertificate> {

    public SchoolCertificateService() {
        super(SchoolCertificate.class, DaoFactory.getDaoFactory().getSchoolCertificateDao());
    }

    @Override
    public void save(SchoolCertificate instance) {
        instance.setAverageScore(calculateAverageScore(instance));
        super.save(instance);
    }

    private double calculateAverageScore(SchoolCertificate instance) {
        final Map<Subject, Integer> schoolScoreMap = instance.getSubjects();
        final Integer schoolScoreSum = schoolScoreMap.values().stream()
            .mapToInt(Integer::intValue)
            .sum();
        // return average rounded by 2 decimal places
        return Math.round(100.0 * schoolScoreSum / schoolScoreMap.size()) / 100.0;
    }

}