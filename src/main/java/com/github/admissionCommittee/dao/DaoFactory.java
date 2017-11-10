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
    private static volatile DaoFactory daoFactory;

    public static DaoFactory getDaoFactory() {
        if (daoFactory == null) {
            synchronized (DaoFactory.class) {
                if (daoFactory == null) {
                    daoFactory = new DaoFactory();
                }
            }
        }
        return daoFactory;
    }

    public ExamCertificateDao getExamCertificateDao() {
        if (examCertificateDao == null) {
            examCertificateDao = new ExamCertificateDao();
        }
        return examCertificateDao;
    }

    public SchoolCertificateDao getSchoolCertificateDao() {
        if (schoolCertificateDao == null) {
            schoolCertificateDao = new SchoolCertificateDao();
        }
        return schoolCertificateDao;
    }

    public SheetDao getSheetDao() {
        if (sheetDao == null) {
            sheetDao = new SheetDao();
        }
        return sheetDao;
    }

    public SubjectDao getSubjectDao() {
        if (subjectDao == null) {
            subjectDao = new SubjectDao();
        }
        return subjectDao;
    }

    public UserDao getUserDao() {
        if (userDao == null) {
            userDao = new UserDao();
        }
        return userDao;
    }

    public FacultyDao getFacultyDao() {
        if (facultyDao == null) {
            facultyDao = new FacultyDao();
        }
        return facultyDao;
    }
}