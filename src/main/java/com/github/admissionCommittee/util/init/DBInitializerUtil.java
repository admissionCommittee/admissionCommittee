package com.github.admissionCommittee.util.init;

import com.github.admissionCommittee.model.ExamCertificate;
import com.github.admissionCommittee.model.SchoolCertificate;
import com.github.admissionCommittee.model.Subject;
import com.github.admissionCommittee.model.User;
import com.github.admissionCommittee.model.enums.SubjectNameEnum;
import com.github.admissionCommittee.model.enums.UserAttendeeState;
import com.github.admissionCommittee.model.enums.UserTypeEnum;
import com.github.admissionCommittee.service.ServiceFactory;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@AllArgsConstructor
public final class DBInitializerUtil implements InitializerUtil {
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
                (3, DBInitializerUtil.class.getResource
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

              //  new DBInitializerUtil().init(30, "specified separalty",
                //"specified separatly");

        Subject subject = new Subject();
        subject.setName(SubjectNameEnum.RUSSIAN);
        ServiceFactory.getServiceFactory().getSubjectService().save(subject);
        User user = new User
                (UserAttendeeState.CHALLENGER, UserTypeEnum.USER, "32423",
                        "3749", "}{P", "@mail.ar", "12", LocalDate.of
                        (2018, 11, 1));


        List<String> errors1 = ServiceFactory.getServiceFactory().getUserService
                ().save(user);


        Map<Subject, Integer> subjectIntegerHashMap = new HashMap<>();
        subjectIntegerHashMap.put(subject,-5);
        List<String> errors2 = ServiceFactory.getServiceFactory()
                .getSchoolCertificateService().save(
                new SchoolCertificate(user, 2017, subjectIntegerHashMap)
        );


        ExamCertificate examCertificate = new ExamCertificate(user,1700,subjectIntegerHashMap);

        List<String> errors3 = ServiceFactory.getServiceFactory()
                .getExamCertificateService().save
                (examCertificate);
        System.out.println("errors1"+errors1);
        System.out.println("errors2"+errors2);
        System.out.println("errors2"+errors3);
        /*Faculty faculty = ServiceFactory.getServiceFactory()
                .getFacultyService().get(1);
        //check
        System.out.println("Faculty: "+faculty);
        System.out.println("Get all students by faculty: "+ServiceFactory.getServiceFactory()
                .getSheetService()
                .getByFaculty
                (faculty));

        List<User> byFacultyEnlisted = ServiceFactory.getServiceFactory()
                .getUserService().getByFacultyEnlisted(ServiceFactory.getServiceFactory()
                        .getFacultyService().get(1));
        System.out.println("Get all students by faculty enlisted: "+byFacultyEnlisted);

        */
        sessionFactory.close();

    }
}