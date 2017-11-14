package com.github.admissionCommittee.util.init;

import com.github.admissionCommittee.model.Faculty;
import com.github.admissionCommittee.model.SchoolCertificate;
import com.github.admissionCommittee.model.Subject;
import com.github.admissionCommittee.model.User;
import com.github.admissionCommittee.service.FacultyService;
import com.github.admissionCommittee.service.SchoolCertificateService;
import com.github.admissionCommittee.service.SubjectService;
import com.github.admissionCommittee.service.UserService;
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
        final UserService userService = new UserService();
        userService.save(userList);
        System.out.println("USERS INIT DONE");

        //init subjects
        List<Subject> subjectList = new SubjectInitializerUtil().initEntities
                (11, "no input", "no output");
        final SubjectService subjectService = new SubjectService();
        System.out.println("SUBJ" + subjectList);
        subjectService.save(subjectList);
        System.out.println("SUBJECTS INIT DONE");

        // init faculties //TODO check
        List<Faculty> facultyList = new FacultyInitializerUtil().initEntities
                (17, DBInitializerUtil.class.getResource
                        ("/db/FacultyInitData" +
                        ".txt").getPath().replaceFirst("^/(.:/)"
                        , "$1"), "no output");
        FacultyService facultyService = new FacultyService();
        facultyService.save(facultyList);
        System.out.println("FACULTIES INIT DONE");

        //init school certificates
        List<SchoolCertificate> schoolCertificates = new
                SchoolCertificateInitializerUtil().initEntities(10,
                "no input", "no output");
        SchoolCertificateService schoolCertificateService = new
                SchoolCertificateService();
        schoolCertificateService.save(schoolCertificates);
        System.out.println("SCHOOL CERTIFICATES INIT DONE");

       /* // init exam certificates
        final ExamCertificate examCertificateIvanov = new ExamCertificate(
                userService.get(userList.get(0).getId()),
                2017,
                new HashMap<Subject, Integer>() {{
                    put(subjectPhysics, 95);
                    put(subjectChemistry, 75);
                    put(subjectBiology, 91);
                }}
        );
        final ExamCertificate examCertificateVolkova = new ExamCertificate(
                userList.get(1),
                2017,
                new HashMap<Subject, Integer>() {{
                    put(subjectPhysics, 83);
                    put(subjectInformatics, 96);
                }}
        );
        final ExamCertificate examCertificateAutlev = new ExamCertificate(
                userList.get(2),
                2017,
                new HashMap<Subject, Integer>() {{
                    put(subjectChemistry, 82);
                    put(subjectBiology, 80);
                }}
        );
        final ExamCertificate examCertificateKanina = new ExamCertificate(
                userList.get(3),
                2017,
                new HashMap<Subject, Integer>() {{
                    put(subjectEnglish, 88);
                    put(subjectHistory, 87);
                }}
        );
        final ExamCertificate examCertificateBorisenko = new ExamCertificate(
                userList.get(4),
                2017,
                new HashMap<Subject, Integer>() {{
                    put(subjectPhysics, 77);
                    put(subjectInformatics, 96);
                    put(subjectEnglish, 86);
                }}
        );
        final ExamCertificate examCertificateSkokova = new ExamCertificate(
                userList.get(5),
                2017,
                new HashMap<Subject, Integer>() {{
                    put(subjectPhysics, 96);
                    put(subjectInformatics, 94);
                }}
        );
        final ExamCertificate examCertificateSaprunov = new ExamCertificate(
                userList.get(6),
                2017,
                new HashMap<Subject, Integer>() {{
                    put(subjectEnglish, 95);
                    put(subjectHistory, 75);
                }}
        );
        final ExamCertificate examCertificateNovikova = new ExamCertificate(
                userList.get(7),
                2017,
                new HashMap<Subject, Integer>() {{
                    put(subjectChemistry, 86);
                    put(subjectBiology, 79);
                }}
        );
        final ExamCertificate examCertificateMakarenko = new ExamCertificate(
                userList.get(8),
                2017,
                new HashMap<Subject, Integer>() {{
                    put(subjectChemistry, 83);
                    put(subjectBiology, 97);
                }}
        );
        final ExamCertificate examCertificateKoshkin = new ExamCertificate(
                userList.get(9),
                2017,
                new HashMap<Subject, Integer>() {{
                    put(subjectPhysics, 88);
                    put(subjectInformatics, 96);
                    put(subjectBiology, 85);
                }}
        );

        final ExamCertificateService examCertificateService = new
                ExamCertificateService();
        examCertificateService.save(examCertificateIvanov);
        examCertificateService.save(examCertificateVolkova);
        examCertificateService.save(examCertificateAutlev);
        examCertificateService.save(examCertificateKanina);
        examCertificateService.save(examCertificateBorisenko);
        examCertificateService.save(examCertificateSkokova);
        examCertificateService.save(examCertificateSaprunov);
        examCertificateService.save(examCertificateNovikova);
        examCertificateService.save(examCertificateMakarenko);
        examCertificateService.save(examCertificateKoshkin);

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
