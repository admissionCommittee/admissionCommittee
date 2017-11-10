package com.github.admissionCommittee.service;

import com.github.admissionCommittee.dao.DaoFactory;
import com.github.admissionCommittee.model.ExamCertificate;
import com.github.admissionCommittee.model.Sheet;
import com.github.admissionCommittee.dao.ExamCertificateDao;
import com.github.admissionCommittee.dao.SheetDao;
import com.github.admissionCommittee.model.Subject;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ExamCertificateService extends GenericService<ExamCertificate> {

    public ExamCertificateService() {
        super(ExamCertificate.class, DaoFactory.getExamCertificateDao());
    }

    @Override
    public void save(ExamCertificate examCertificate) {
        super.save(examCertificate);

        //TODO calculate average and execute
        new SheetService().save(new Sheet(examCertificate.getUser(), examCertificate
                .getUser().getSheet().getFaculty(), sumExamScore(examCertificate),
                examCertificate.getUser().getSheet().getAverageSchoolCertificateScore()));
    }

    private int sumExamScore(ExamCertificate examCertificate) {
        Set<Subject> subjects = examCertificate.getUser().getSheet().getFaculty().getSubjects();
        Map<Subject, Integer> subjects1 = examCertificate.getSubjects();
        return subjects1.keySet().parallelStream()
                .filter(subjects::contains)
                .mapToInt(subjects1::get)
                .sum();
    }
}

