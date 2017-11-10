package com.github.admissionCommittee.dao;

public class DaoFactory {
    private DaoFactory() {
    }

    private static ExamCertificateDao examCertificateDao;
    private static FacultyDao facultyDao;
    private static SchoolCertificateDao schoolCertificateDao;
    private static SheetDao sheetDao;
    private static SubjectDao subjectDao;
    private static UserDao userDao;

    private static volatile DaoFactory daoFactory = new DaoFactory();

    public static synchronized DaoFactory getDaoFactory() {
        return daoFactory;
    }

    public static ExamCertificateDao getExamCertificateDao() {
        if (examCertificateDao == null) {
            examCertificateDao = new ExamCertificateDao();
        }
        return examCertificateDao;
    }

    public static SchoolCertificateDao getSchoolCertificateDao() {
        if (schoolCertificateDao == null) {
            schoolCertificateDao = new SchoolCertificateDao();
        }
        return schoolCertificateDao;
    }

    public static SheetDao getSheetDao() {
        if (sheetDao == null) {
            sheetDao = new SheetDao();
        }
        return sheetDao;
    }

    public static SubjectDao getSubjectDao() {
        if (subjectDao == null) {
            subjectDao = new SubjectDao();
        }
        return subjectDao;
    }

    public static UserDao getUserDao() {
        if (userDao == null) {
            userDao = new UserDao();
        }
        return userDao;
    }

    public static FacultyDao getFacultyDao() {
        if (facultyDao == null) {
            facultyDao = new FacultyDao();
        }
        return facultyDao;
    }
}