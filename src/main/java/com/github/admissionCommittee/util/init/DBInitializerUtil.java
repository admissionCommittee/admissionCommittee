package com.github.admissionCommittee.util.init;

import lombok.AllArgsConstructor;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@AllArgsConstructor
final class DBInitializerUtil implements InitializerUtil {
    private static final Logger log = LoggerFactory.getLogger
            (DBInitializerUtil.class);

    @Override
    public void init(int usersNumber, String outputFile, String inputFile) {

        log.info(String.format("Loading users from: %s", DBInitializerUtil.class
                .getResource("/db/UserInitData.txt").getPath().replaceFirst(
                        "^/(.:/)", "$1")));

        // init users without faculty, certificates and sheet and validate
        new UserInitializerUtil().init(
                usersNumber, DBInitializerUtil.class.getResource(
                        "/db/UserInitData.txt").getPath().replaceFirst(
                        "^/(.:/)", "$1"),
                "no path");

        //init subjects, validate each one and validate init
        new SubjectInitializerUtil().init
                (11, "no input", "no output");

        // init faculties with obligatory subjects, validate each one,
        // validate init, randomly set for each user, update users
        new FacultyInitializerUtil().init
                (17, DBInitializerUtil.class.getResource
                        ("/db/FacultyInitData" +
                                ".txt").getPath().replaceFirst("^/(.:/)"
                        , "$1"), "no output");

        //init school certificates, validate each one, validate init, set for
        // each user, update users
        new SchoolCertificateInitializerUtil().init(usersNumber,
                "no input", "no output");

        // init exam certificates, validate each one, validate init, set for
        // each user and update users
        new ExamCertificateInitializerUtil().init(usersNumber,
                "no input", "no output");

        new SheetInitializerUtil().init(usersNumber,
                "no input", "no otput");

        log.info(String.format("DB have been initialized successfully, total " +
                "%s users", usersNumber));
    }

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        sessionFactory.close();
    }
}