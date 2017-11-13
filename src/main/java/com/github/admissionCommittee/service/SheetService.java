package com.github.admissionCommittee.service;

import com.github.admissionCommittee.dao.DaoFactory;
import com.github.admissionCommittee.model.ExamCertificate;
import com.github.admissionCommittee.model.Faculty;
import com.github.admissionCommittee.model.SchoolCertificate;
import com.github.admissionCommittee.model.Sheet;
import com.github.admissionCommittee.dao.SheetDao;
import com.github.admissionCommittee.model.Subject;
import com.github.admissionCommittee.model.User;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class SheetService extends GenericService<Sheet> {

    public SheetService() {
        super(Sheet.class, DaoFactory.getDaoFactory().getSheetDao());
    }

    @Override
    public void save(Sheet instance) {
        instance.setSumExamCertificateScore(calculateExamScoreSum(instance));
        instance.setAverageSchoolCertificateScore(calculateSchoolScoreAverage(instance));
        super.save(instance);
    }

    private int calculateExamScoreSum(Sheet instance) {
        final User user = instance.getUser();
        final Faculty faculty = instance.getFaculty();
        final ExamCertificate userExamCertificate = user.getExamCertificate();
        final Map<Subject, Integer> examScoreMap = userExamCertificate.getSubjects();
        final Set<Subject> facultySubjects = faculty.getSubjects();
        return examScoreMap.keySet().stream()
            .filter(facultySubjects::contains)
            .mapToInt(examScoreMap::get)
            .sum();
    }

    private double calculateSchoolScoreAverage(Sheet instance) {
        final User user = instance.getUser();
        final SchoolCertificate userSchoolCertificate = user.getSchoolCertificate();
        final Map<Subject, Integer> schoolScoreMap = userSchoolCertificate.getSubjects();
        final Integer schoolScoreSum = schoolScoreMap.values().stream()
            .mapToInt(Integer::intValue)
            .sum();
        // return average rounded by 2 decimal places
        return Math.round(100.0 * schoolScoreSum / schoolScoreMap.size()) / 100.0;
    }

    public List<Sheet> getByFaculty(Faculty faculty) {
        return ((SheetDao) getDao()).getByFaculty(faculty);
    }

}