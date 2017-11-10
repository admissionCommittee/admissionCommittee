package com.github.admissionCommittee.service;

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

    public ExamCertificateService(ExamCertificateDao examCertificateDao) {
        super(ExamCertificate.class, examCertificateDao);
    }

    @Override
    public void save(ExamCertificate examCertificate) {
        super.save(examCertificate);

        //TODO calculate average and execute
        new SheetService(new SheetDao()).save(new Sheet(examCertificate.getUser(), examCertificate
                .getUser().getSheet().getFaculty(), sumExamScore(examCertificate),
                examCertificate.getUser().getSheet().getAverageSchoolCertificateScore()));
    }

    private double sumExamScore(ExamCertificate examCertificate) {
        Set<Subject> subjects = examCertificate.getUser().getSheet().getFaculty().getSubjects();
        Map<Subject, Integer> subjects1 = examCertificate.getSubjects();
        return subjects1.keySet().parallelStream()
                .filter(subjects::contains)
                .mapToInt(subjects1::get)
                .sum();
    }
}

