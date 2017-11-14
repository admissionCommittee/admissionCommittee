package com.github.admissionCommittee.util.init;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.SessionFactory;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class DBInitializerUtil {
    static void initDatabase() {
        // init users without faculty, certificates and sheet and validate
        new UserInitializerUtil().initEntities(
                10, DBInitializerUtil.class.getResource("/db" +
                        "/UserInitData.txt").getPath().replaceFirst("^/(.:/)"
                        , "$1"), "no path");

        //init subjects, validate each one and validate init
        new SubjectInitializerUtil().initEntities
                (11, "no input", "no output");

        // init faculties with obligatory subjects, validate each one,
        // validate init, randomly set for each user, update users
        new FacultyInitializerUtil().initEntities
                (17, DBInitializerUtil.class.getResource
                        ("/db/FacultyInitData" +
                                ".txt").getPath().replaceFirst("^/(.:/)"
                        , "$1"), "no output");

        //init school certificates, validate each one, validate init, set for
        // each user, update users
        new SchoolCertificateInitializerUtil().initEntities(10,
                "no input", "no output");

        // init exam certificates, validate each one, validate init, set for
        // each user and update users
        new ExamCertificateInitializerUtil().initEntities(10,
                "no input", "no output");

        new SheetInitializerUtil().initEntities(10,
                "no input", "no otput");

    }

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        sessionFactory.close();
    }
}