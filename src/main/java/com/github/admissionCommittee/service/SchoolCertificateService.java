package com.github.admissionCommittee.service;

import com.github.admissionCommittee.dao.DaoFactory;
import com.github.admissionCommittee.model.SchoolCertificate;
import com.github.admissionCommittee.model.Sheet;
import com.github.admissionCommittee.dao.SchoolCertificateDao;
import com.github.admissionCommittee.dao.SheetDao;

public class SchoolCertificateService extends
        GenericService<SchoolCertificate> {
    public SchoolCertificateService() {
        super(SchoolCertificate.class, DaoFactory.getSchoolCertificateDao());
    }

    @Override
    public void save(SchoolCertificate schoolCertificate) {
        super.save(schoolCertificate);

        //TODO calculate average and execute
        new SheetService().save(new Sheet(schoolCertificate.getUser(),
                schoolCertificate.getUser().getSheet().getFaculty(), schoolCertificate.getUser()
                .getSheet().getSumExamCertificateScore(), averageSchoolCertificateScore
                (schoolCertificate)));
    }

    private double averageSchoolCertificateScore(SchoolCertificate schoolCertificate) {
        Integer sum = schoolCertificate.getSubjects().values().parallelStream()
                .reduce(0, (a, b) -> a + b);
        return (double) sum / schoolCertificate.getSubjects().size();
    }
}