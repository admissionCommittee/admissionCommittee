package com.github.admissionCommittee.service;

import com.github.admissionCommittee.model.AbstractEntity;
import com.github.admissionCommittee.model.ExamCertificate;
import com.github.admissionCommittee.model.Faculty;
import com.github.admissionCommittee.model.SchoolCertificate;
import com.github.admissionCommittee.model.Sheet;
import com.github.admissionCommittee.model.Subject;
import com.github.admissionCommittee.model.User;

public class ServiceFactory {
    private ServiceFactory() {
    }

    private static ExamCertificateService examCertificateService;
    private static FacultyService facultyService;
    private static SchoolCertificateService schoolCertificateService;
    private static SheetService sheetService;
    private static SubjectService subjectService;
    private static UserService userService;
    private static volatile ServiceFactory serviceFactory;

    public static ServiceFactory getServiceFactory() {
        if (serviceFactory == null) {
            synchronized (ServiceFactory.class) {
                if (serviceFactory == null) {
                    serviceFactory = new ServiceFactory();
                }
            }
        }
        return serviceFactory;
    }

    public ExamCertificateService getExamCertificateService() {
        if (examCertificateService == null) {
            synchronized (ServiceFactory.class) {
                if (examCertificateService == null) {
                    examCertificateService = new ExamCertificateService();
                }
            }
        }
        return examCertificateService;
    }

    public SchoolCertificateService getSchoolCertificateService() {
        if (schoolCertificateService == null) {
            synchronized (ServiceFactory.class) {
                if (schoolCertificateService == null) {
                    schoolCertificateService = new SchoolCertificateService();
                }
            }
        }
        return schoolCertificateService;
    }

    public SheetService getSheetService() {
        if (sheetService == null) {
            synchronized (ServiceFactory.class) {
                if (sheetService == null) {
                    sheetService = new SheetService();
                }
            }
        }
        return sheetService;
    }

    public SubjectService getSubjectService() {
        if (subjectService == null) {
            synchronized (ServiceFactory.class) {
                if (subjectService == null) {
                    subjectService = new SubjectService();
                }
            }
        }
        return subjectService;
    }

    public UserService getUserService() {
        if (userService == null) {
            synchronized (ServiceFactory.class) {
                if (userService == null) {
                    userService = new UserService();
                }
            }
        }
        return userService;
    }

    public FacultyService getFacultyService() {
        if (facultyService == null) {
            synchronized (ServiceFactory.class) {
                if (facultyService == null) {
                    facultyService = new FacultyService();
                }
            }
        }
        return facultyService;
    }

    public GenericService getService(Class<? extends AbstractEntity>
                                                    modelType) {
        if (modelType == User.class) {
            return userService;
        }
        if (modelType == Subject.class) {
            return subjectService;
        }
        if (modelType == Faculty.class) {
            return facultyService;
        }
        if (modelType == SchoolCertificate.class) {
            return schoolCertificateService;
        }
        if (modelType == ExamCertificate.class) {
            return examCertificateService;
        }
        if (modelType == Sheet.class) {
            return sheetService;
        }
        return null;
    }
}