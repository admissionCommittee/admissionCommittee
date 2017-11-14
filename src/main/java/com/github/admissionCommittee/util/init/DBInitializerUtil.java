package com.github.admissionCommittee.util.init;

import lombok.AllArgsConstructor;
import org.hibernate.SessionFactory;

@AllArgsConstructor
final class DBInitializerUtil implements InitializerUtil {

    @Override
    public void init(int usersNumber, String outputFile, String inputFile) {
        // init users without faculty, certificates and sheet and validate

        System.out.println(DBInitializerUtil.class.getResource(
                "/db/UserInitData.txt").getPath().replaceFirst(
                "^/(.:/)", "$1"));

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

    }

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        sessionFactory.close();
    }
}