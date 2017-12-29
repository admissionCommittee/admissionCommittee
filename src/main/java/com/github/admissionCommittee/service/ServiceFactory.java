package com.github.admissionCommittee.service;

import com.github.admissionCommittee.model.AbstractEntity;
import com.github.admissionCommittee.model.ExamCertificate;
import com.github.admissionCommittee.model.Faculty;
import com.github.admissionCommittee.model.SchoolCertificate;
import com.github.admissionCommittee.model.Sheet;
import com.github.admissionCommittee.model.Subject;
import com.github.admissionCommittee.model.User;

public class ServiceFactory{
    private ServiceFactory() {
    }

    public static ExamCertificateService getExamCertificateService() {
        return new ExamCertificateService();
    }

    public static SchoolCertificateService getSchoolCertificateService() {
        return new SchoolCertificateService();
    }

    public static SheetService getSheetService() {
        return new SheetService();
    }

    public static SubjectService getSubjectService() {
        return new SubjectService();
    }

    public static UserService getUserService() {
        return new UserService();
    }

    public static FacultyService getFacultyService() {
        return new FacultyService();
    }

    public static GenericService getService(Class<? extends AbstractEntity>
                                             modelType) {
        if (modelType == User.class) {
            return new UserService();
        }
        if (modelType == Subject.class) {
            return new SubjectService();
        }
        if (modelType == Faculty.class) {
            return new FacultyService();
        }
        if (modelType == SchoolCertificate.class) {
            return new SchoolCertificateService();
        }
        if (modelType == ExamCertificate.class) {
            return new ExamCertificateService();
        }
        if (modelType == Sheet.class) {
            return new SheetService();
        }
        return null;
    }
}