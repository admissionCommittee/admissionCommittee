package com.github.admissionCommittee.util.init;

import com.github.admissionCommittee.model.ExamCertificate;
import com.github.admissionCommittee.model.Faculty;
import com.github.admissionCommittee.model.SchoolCertificate;
import com.github.admissionCommittee.model.Subject;
import com.github.admissionCommittee.model.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hibernate.SessionFactory;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class DBInitializerUtil {


    static void initDatabase() {
        // init users
        List<User> userList = new UserInitializerUtil().initEntities(
                10, DBInitializerUtil.class.getResource("/db" +
                        "/UserInitData.txt").getPath().replaceFirst("^/(.:/)"
                        , "$1"), "no path");


        //init subjects
        List<Subject> subjectList = new SubjectInitializerUtil().initEntities
                (11, "no input", "no output");

        // init faculties //TODO check
        List<Faculty> facultyList = new FacultyInitializerUtil().initEntities
                (17, DBInitializerUtil.class.getResource
                        ("/db/FacultyInitData" +
                                ".txt").getPath().replaceFirst("^/(.:/)"
                        , "$1"), "no output");

        //init school certificates
        List<SchoolCertificate> schoolCertificates = new
                SchoolCertificateInitializerUtil().initEntities(10,
                "no input", "no output");


        // init exam certificates
        List<ExamCertificate> examCertificates = new
                ExamCertificateInitializerUtil().initEntities(10,
                "no input", "no output");


 /*
        // init sheets
        final Sheet sheetIvanov = new Sheet(
                userService.get(userList.get(0).getId()), facultyStomatology,
                0, 0
        );
        final Sheet sheetVolkova = new Sheet(
                userService.get(userList.get(1).getId()), facultyPhysical, 0, 0
        );
        final Sheet sheetAutlev = new Sheet(
                userService.get(userList.get(2).getId()), facultyStomatology,
                0, 0
        );
        final Sheet sheetKanina = new Sheet(
                userService.get(userList.get(3).getId()), facultyHistorical,
                0, 0
        );
        final Sheet sheetBorisenko = new Sheet(
                userService.get(userList.get(4).getId()), facultyPhysical, 0, 0
        );
        final Sheet sheetSkokova = new Sheet(
                userService.get(userList.get(5).getId()), facultyPhysical, 0, 0
        );
        final Sheet sheetSaprunov = new Sheet(
                userService.get(userList.get(6).getId()), facultyHistorical,
                0, 0
        );
        final Sheet sheetNovikova = new Sheet(
                userService.get(userList.get(7).getId()), facultyStomatology,
                0, 0
        );
        final Sheet sheetMakarenko = new Sheet(
                userService.get(userList.get(8).getId()), facultyStomatology,
                0, 0
        );
        final Sheet sheetKoshkin = new Sheet(
                userService.get(userList.get(9).getId()), facultyPhysical, 0, 0
        );

        final SheetService sheetService = new SheetService();
        sheetService.save(sheetIvanov);
        sheetService.save(sheetVolkova);
        sheetService.save(sheetAutlev);
        sheetService.save(sheetKanina);
        sheetService.save(sheetBorisenko);
        sheetService.save(sheetSkokova);
        sheetService.save(sheetSaprunov);
        sheetService.save(sheetNovikova);
        sheetService.save(sheetMakarenko);
        sheetService.save(sheetKoshkin);

        // ---------------------------

        System.out.println(userService.getByMail
                ("Timur_Aliberdov@epam.com"));
//        final List<Sheet> byFaculty1 = sheetService.getByFaculty
// (facultyPhysical);
//        final List<Sheet> byFaculty2 = sheetService.getByFaculty
// (facultyStomatology);
//        final List<Sheet> byFaculty3 = sheetService.getByFaculty
// (facultyHistorical);
//        final User userByMail2 = userService.getByMail("kad@epam.com");
//        final User userByMail3 = userService.getByMail("kad@epam.com");
*/
    }

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        sessionFactory.close();
    }

}
