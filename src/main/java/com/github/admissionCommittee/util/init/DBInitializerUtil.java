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

        System.out.println(pathToLog);

        //print errors to log
        ValidatorUtil.printErrorsToFileSystem(errorsLog,pathToLog );

        log.info(String.format("DB have been initialized read log, total " +
                "%s users", usersNumber));
        return errorsLog;
    }

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        new DBInitializerUtil().init(1500,
                "specified separalty",
                "specified separatly");

       /* User user = ServiceFactory.getUserService().get(3);
        Faculty faculty = user.getFaculty();
        System.out.println(faculty);

        SheetService sheetService = ServiceFactory.getSheetService();
        List<Sheet> byFaculty = sheetService.getByFaculty(faculty);
        System.out.println(byFaculty.size());

        for (int i = 1; i < 50; i++) {

            List<Object> byFaculty1 = sheetService.getByFaculty(faculty, i);

            System.out.println("Pages Number"+byFaculty.get(0));
            System.out.println("Sheet's list"+ byFaculty1.get(1));
            System.out.println(((List<Sheet>)byFaculty1.get(1)).size());
        }*/

        /*Subject subject = new Subject();
        subject.setName(SubjectNameEnum.RUSSIAN);
        SubjectService subjectService = ServiceFactory.)
                .getSubjectService();
        subjectService.save(subject);
        User user = new User
                (UserAttendeeState.CHALLENGER, UserTypeEnum.USER, "32423",
                        "qwe", "qwre", "re@mail.ar", "2356262F",
                        LocalDate.of
                                (2000, 11, 1));

        UserService userService = ServiceFactory.)
                .getUserService();
        Set<String> errors1 = userService.save(user);


        Map<Subject, Integer> subjectIntegerHashMap = new HashMap<>();
        subjectIntegerHashMap.put(subject, -5);
        Set<String> errors2 = ServiceFactory.)
                .getSchoolCertificateService().save(
                        new SchoolCertificate(user, 2017, subjectIntegerHashMap)
                );


        ExamCertificate examCertificate = new ExamCertificate(user, 1700,
                subjectIntegerHashMap);

        Set<String> errors3 = ServiceFactory.)
                .getExamCertificateService().save
                        (examCertificate);
        List<User> all = userService.getAll();
        System.out.println(all);
        System.out.println("errors1" + errors1);
        System.out.println("errors1" + errors1.size());
        System.out.println("errors2" + errors2);
        System.out.println("errors2" + errors2.size());
        System.out.println("errors2" + errors3);
        System.out.println("errors2" + errors3.size());
        /*Faculty faculty = ServiceFactory.)
                .getFacultyService().get(1);
        //check
        System.out.println("Faculty: "+faculty);
        System.out.println("Get all students by faculty: "+ServiceFactory
        ()
                .getSheetService()
                .getByFaculty
                (faculty));

        List<User> byFacultyEnlisted = ServiceFactory.)
                .getUserService().getByFacultyEnlisted(ServiceFactory
                ()
                        .getFacultyService().get(1));
        System.out.println("Get all students by faculty enlisted:
        "+byFacultyEnlisted);

*/
        sessionFactory.close();

    }
}
