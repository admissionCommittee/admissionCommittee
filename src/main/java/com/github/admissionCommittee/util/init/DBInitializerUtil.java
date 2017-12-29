package com.github.admissionCommittee.util.init;

import com.github.admissionCommittee.model.Faculty;
import com.github.admissionCommittee.model.Sheet;
import com.github.admissionCommittee.model.User;
import com.github.admissionCommittee.service.ServiceFactory;
import com.github.admissionCommittee.service.SheetService;
import com.github.admissionCommittee.util.validate.ValidatorUtil;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

//@AllArgsConstructor
public final class DBInitializerUtil implements InitializerUtil {
    private static final Logger log = LoggerFactory.getLogger
            (DBInitializerUtil.class);

    @Override
    public Set<String> init(int usersNumber, String outputFile, String
            inputFile) {
        Set<String> errorsLog = new LinkedHashSet<>();

        log.info(String.format("Loading users from: %s", DBInitializerUtil.class
                .getResource("/db/UserInitData.txt").getPath().replaceFirst(
                        "^/(.:/)", "$1")));

        // init users without faculty, certificates and sheet and validate
        errorsLog.addAll(new UserInitializerUtil().init(
                usersNumber, DBInitializerUtil.class.getResource(
                        "/db/UserInitData.txt").getPath().replaceFirst(
                        "^/(.:/)", "$1"),
                "no path"));

        //init subjects, validate each one and validate init
        errorsLog.addAll(new SubjectInitializerUtil().init
                (11, "no input", "no output"));

        // init faculties with obligatory subjects, validate each one,
        // validate init, randomly set for each user, update users
        errorsLog.addAll(new FacultyInitializerUtil().init
                (3, DBInitializerUtil.class.getResource
                        ("/db/FacultyInitData" +
                                ".txt").getPath().replaceFirst("^/(.:/)"
                        , "$1"), "no output"));

        //init school certificates, validate each one, validate init, set for
        // each user, update users
        errorsLog.addAll(new SchoolCertificateInitializerUtil().init
                (usersNumber,
                        "no input", "no output"));

        // init exam certificates, validate each one, validate init, set for
        // each user and update users
        errorsLog.addAll(new ExamCertificateInitializerUtil().init(usersNumber,
                "no input", "no output"));

        errorsLog.addAll(new SheetInitializerUtil().init(usersNumber,
                "no input", "no otput"));

        String pathToLog = DBInitializerUtil.class.getResource(
                "/db/ErrorsLog.txt").getPath().replaceFirst(
                "^/(.:/)", "$1").replace("build/resources/main",
                "src/main/resources");

        log.info(String.format("Path to errors log: %s.", pathToLog));

        //print errors to log
        ValidatorUtil.printErrorsToFileSystem(errorsLog, pathToLog);

        log.info(String.format("DB have been initialized read log, total " +
                "%s users", usersNumber));
        return errorsLog;
    }

    public static void main(String[] args) {
            /*   SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        Set<String> init = new DBInitializerUtil().init(100,
                "specified separalty",
                "specified separatly");
        System.out.println(init);


        sessionFactory.close();*/

    }
}
