package com.github.admissionCommittee.service;

import com.github.admissionCommittee.dao.DaoFactory;
import com.github.admissionCommittee.dao.SheetDao;
import com.github.admissionCommittee.model.ExamCertificate;
import com.github.admissionCommittee.model.Faculty;
import com.github.admissionCommittee.model.Sheet;
import com.github.admissionCommittee.model.Subject;
import com.github.admissionCommittee.model.User;
import com.github.admissionCommittee.util.validate.SheetValidatorUtil;

import java.util.*;

public class SheetService extends GenericService<Sheet> {

    public SheetService() {
        super(Sheet.class, DaoFactory.getDaoFactory().getSheetDao());
    }

    /**
     * Save instance to DB, validation included, if no valid - errorLog
     * adding, no saving; calculating sum score.
     *
     * @param instance instance to save
     * @return collection of validation errors
     */
    @Override
    public Set<String> save(Sheet instance) {
        instance.setSumExamCertificateScore(calculateExamScoreSum(instance));
        Set<String> errorsLog = new SheetValidatorUtil().validate(instance);
        if (errorsLog.size() == 0) {
            super.save(instance);
        }
        return new LinkedHashSet<>();
    }

    public Sheet getByUser(User user) {
        return DaoFactory.getDaoFactory().getSheetDao().getByUser(user);
    }

    private int calculateExamScoreSum(Sheet instance) {
        final User user = instance.getUser();
        final Faculty faculty = instance.getFaculty();
        final ExamCertificate userExamCertificate = user.getExamCertificate();
        final Map<Subject, Integer> examScoreMap = userExamCertificate
                .getSubjects();
        final Set<Subject> facultySubjects = faculty.getSubjects();
        return examScoreMap.keySet().stream()
                .filter(facultySubjects::contains)
                .mapToInt(examScoreMap::get)
                .sum();
    }

    //Ranged by 2 parameters
    public List<Sheet> getByFaculty(Faculty faculty) {
        final List<Sheet> byFaculty = DaoFactory.getDaoFactory().getSheetDao().getByFaculty
                (faculty);
        byFaculty.sort((sheet1, sheet2) -> {
            // descending order by exams
            final int compare = Integer.compare
                    (sheet2.getSumExamCertificateScore(),
                            sheet1.getSumExamCertificateScore());
            if (compare == 0) {
                // descending order by school certificate
                return Double.compare(sheet2.getUser().getSchoolCertificate()
                                .getAverageScore(),
                        sheet1.getUser().getSchoolCertificate()
                                .getAverageScore());
            }
            return compare;
        });
        final int delta = byFaculty.size() - faculty.getPeopleLimit();
        if (delta > 0) {
            return byFaculty.subList(0, faculty.getPeopleLimit());
        }
        return byFaculty;
    }


    public List<Object> getByFaculty(Faculty faculty, int pageNumber) {
        List<Sheet> byFaculty = getByFaculty(faculty);
        int linesPerPage = 30;

        int pagesNumber = byFaculty.size() / linesPerPage + 1;
        List<Sheet> linesForPage = null;
        List<Object> resultList = new ArrayList<>();
        resultList.add(pageNumber);

        //check page
        if (pageNumber > pagesNumber) {
            return null;
        } else {
            if (pageNumber == pagesNumber) {
                linesForPage = byFaculty.subList((pageNumber - 1) * 30, byFaculty
                        .size());
                resultList.add(linesForPage);
            } else {
                linesForPage = byFaculty.subList((pageNumber - 1) * 30, pageNumber *
                        30);
                resultList.add(linesForPage);
            }
            return resultList;
        }
    }
}