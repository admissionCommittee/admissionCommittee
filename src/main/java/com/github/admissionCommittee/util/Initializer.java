package com.github.admissionCommittee.util;

import com.github.admissionCommittee.dao.DaoFactory;
import com.github.admissionCommittee.dao.SubjectDao;
import com.github.admissionCommittee.model.ExamCertificate;
import com.github.admissionCommittee.model.Faculty;
import com.github.admissionCommittee.model.SchoolCertificate;
import com.github.admissionCommittee.model.Sheet;
import com.github.admissionCommittee.model.Subject;
import com.github.admissionCommittee.model.User;
import com.github.admissionCommittee.model.enums.SubjectNameEnum;
import com.github.admissionCommittee.model.enums.UserTypeEnum;
import com.github.admissionCommittee.service.ExamCertificateService;
import com.github.admissionCommittee.service.FacultyService;
import com.github.admissionCommittee.service.SchoolCertificateService;
import com.github.admissionCommittee.service.SheetService;
import com.github.admissionCommittee.service.UserService;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
final class Initializer {

    static void initDatabase() {

        // init users
        final User userIvanov = new User(
            UserTypeEnum.USER, "Иванов", "Владимир", "Михайлович",
            "ivm@epam.com", "123", LocalDate.of(1997, Month.AUGUST, 11));
        final User userVolkova = new User(
            UserTypeEnum.USER, "Волкова", "Анна", "Сергеевна",
            "vas@epam.com", "123", LocalDate.of(1999, Month.JULY, 23));
        final User userAutlev = new User(
            UserTypeEnum.USER, "Аутлев", "Тимур", "Валерьевич",
            "atv@epam.com", "123", LocalDate.of(2000, Month.JANUARY, 1));
        final User userKanina = new User(
            UserTypeEnum.USER, "Канина", "Дарья", "Алексеевна",
            "kda@epam.com", "123", LocalDate.of(1999, Month.DECEMBER, 3));
        final User userBorisenko = new User(
            UserTypeEnum.USER, "Борисенко", "Константин", "Александрович",
            "bka@epam.com", "123", LocalDate.of(1999, Month.JULY, 20));
        final User userSkokova = new User(
            UserTypeEnum.USER, "Скокова", "Виктория", "Викторовна",
            "svv@epam.com", "123", LocalDate.of(2000, Month.APRIL, 19));
        final User userSaprunov = new User(
            UserTypeEnum.USER, "Сапрунов", "Степан", "Владимирович",
            "ssv@epam.com", "123", LocalDate.of(1995, Month.APRIL, 21));
        final User userNovikova = new User(
            UserTypeEnum.USER, "Новикова", "Лилиана", "Павловна",
            "nlp@epam.com", "123", LocalDate.of(2000, Month.JANUARY, 9));
        final User userMakarenko = new User(
            UserTypeEnum.USER, "Макаренко", "Данил", "Васильевич",
            "mdv@epam.com", "123", LocalDate.of(1999, Month.OCTOBER, 7));
        final User userKoshkin = new User(
            UserTypeEnum.USER, "Кошкин", "Александр", "Дмитриевич",
            "kad@epam.com", "123", LocalDate.of(1999, Month.MARCH, 17));

        final User adminChugunov = new User(
            UserTypeEnum.ADMIN, "Чугунов", "Леонид", "Александрович",
            "chla@epam.com", "admin", LocalDate.of(1962, Month.MAY, 14));

        final UserService userService = new UserService();
        userService.save(userIvanov);
        userService.save(userVolkova);
        userService.save(userAutlev);
        userService.save(userKanina);
        userService.save(userBorisenko);
        userService.save(userSkokova);
        userService.save(userSaprunov);
        userService.save(userNovikova);
        userService.save(userMakarenko);
        userService.save(userKoshkin);
        userService.save(adminChugunov);

        // init subjects
        final Subject subjectPhysics = new Subject(SubjectNameEnum.PHYSICS);
        final Subject subjectInformatics = new Subject(SubjectNameEnum.INFORMATICS);
        final Subject subjectChemistry = new Subject(SubjectNameEnum.CHEMISTRY);
        final Subject subjectBiology = new Subject(SubjectNameEnum.BIOLOGY);
        final Subject subjectEnglish = new Subject(SubjectNameEnum.ENGLISH);
        final Subject subjectHistory = new Subject(SubjectNameEnum.HISTORY);
        final Subject subjectRussian = new Subject(SubjectNameEnum.RUSSIAN);
        final Subject subjectLiterature = new Subject(SubjectNameEnum.LITERATURE);
        final Subject subjectAlgebra = new Subject(SubjectNameEnum.ALGEBRA);
        final Subject subjectGeometry = new Subject(SubjectNameEnum.GEOMETRY);
        final Subject subjectGeography = new Subject(SubjectNameEnum.GEOGRAPHY);

        final SubjectDao subjectDao = DaoFactory.getDaoFactory().getSubjectDao();
        subjectDao.create(subjectPhysics);
        subjectDao.create(subjectInformatics);
        subjectDao.create(subjectChemistry);
        subjectDao.create(subjectBiology);
        subjectDao.create(subjectEnglish);
        subjectDao.create(subjectHistory);
        subjectDao.create(subjectRussian);
        subjectDao.create(subjectLiterature);
        subjectDao.create(subjectAlgebra);
        subjectDao.create(subjectGeometry);
        subjectDao.create(subjectGeography);

        // init faculties
        final Faculty facultyStomatology = new Faculty(
            "Стоматология",
            3,
            new HashSet<>(Arrays.asList(subjectBiology, subjectChemistry))
        );
        final Faculty facultyPhysical = new Faculty(
            "Физический",
            5,
            new HashSet<>(Arrays.asList(subjectPhysics, subjectInformatics))
        );
        final Faculty facultyHistorical = new Faculty(
            "Исторический",
            4,
            new HashSet<>(Arrays.asList(subjectEnglish, subjectHistory))
        );

        final FacultyService facultyService = new FacultyService();
        facultyService.save(facultyStomatology);
        facultyService.save(facultyPhysical);
        facultyService.save(facultyHistorical);

        // init school certificates
        final SchoolCertificate schoolCertificateIvanov = new SchoolCertificate(
            userIvanov,
            2017,
            new HashMap<Subject, Integer>() {{
                put(subjectPhysics, 3);
                put(subjectInformatics, 3);
                put(subjectChemistry, 3);
                put(subjectBiology, 4);
                put(subjectEnglish, 4);
                put(subjectHistory, 4);
                put(subjectRussian, 4);
                put(subjectLiterature, 4);
                put(subjectAlgebra, 3);
                put(subjectGeometry, 3);
                put(subjectGeography, 3);
            }}
        );
        final SchoolCertificate schoolCertificateVolkova = new SchoolCertificate(
            userVolkova,
            2017,
            new HashMap<Subject, Integer>() {{
                put(subjectPhysics, 5);
                put(subjectInformatics, 5);
                put(subjectChemistry, 4);
                put(subjectBiology, 4);
                put(subjectEnglish, 4);
                put(subjectHistory, 3);
                put(subjectRussian, 5);
                put(subjectLiterature, 3);
                put(subjectAlgebra, 5);
                put(subjectGeometry, 5);
                put(subjectGeography, 4);
            }}
        );
        final SchoolCertificate schoolCertificateAutlev = new SchoolCertificate(
            userAutlev,
            2017,
            new HashMap<Subject, Integer>() {{
                put(subjectPhysics, 3);
                put(subjectInformatics, 5);
                put(subjectChemistry, 3);
                put(subjectBiology, 3);
                put(subjectEnglish, 4);
                put(subjectHistory, 4);
                put(subjectRussian, 5);
                put(subjectLiterature, 4);
                put(subjectAlgebra, 3);
                put(subjectGeometry, 4);
                put(subjectGeography, 3);
            }}
        );
        final SchoolCertificate schoolCertificateKanina = new SchoolCertificate(
            userKanina,
            2017,
            new HashMap<Subject, Integer>() {{
                put(subjectPhysics, 5);
                put(subjectInformatics, 3);
                put(subjectChemistry, 3);
                put(subjectBiology, 3);
                put(subjectEnglish, 3);
                put(subjectHistory, 4);
                put(subjectRussian, 5);
                put(subjectLiterature, 4);
                put(subjectAlgebra, 4);
                put(subjectGeometry, 4);
                put(subjectGeography, 4);
            }}
        );
        final SchoolCertificate schoolCertificateBorisenko = new SchoolCertificate(
            userBorisenko,
            2017,
            new HashMap<Subject, Integer>() {{
                put(subjectPhysics, 4);
                put(subjectInformatics, 5);
                put(subjectChemistry, 3);
                put(subjectBiology, 3);
                put(subjectEnglish, 4);
                put(subjectHistory, 3);
                put(subjectRussian, 3);
                put(subjectLiterature, 5);
                put(subjectAlgebra, 3);
                put(subjectGeometry, 3);
                put(subjectGeography, 4);
            }}
        );
        final SchoolCertificate schoolCertificateSkokova = new SchoolCertificate(
            userSkokova,
            2017,
            new HashMap<Subject, Integer>() {{
                put(subjectPhysics, 4);
                put(subjectInformatics, 4);
                put(subjectChemistry, 4);
                put(subjectBiology, 3);
                put(subjectEnglish, 5);
                put(subjectHistory, 3);
                put(subjectRussian, 3);
                put(subjectLiterature, 3);
                put(subjectAlgebra, 5);
                put(subjectGeometry, 5);
                put(subjectGeography, 5);
            }}
        );
        final SchoolCertificate schoolCertificateSaprunov = new SchoolCertificate(
            userSaprunov,
            2017,
            new HashMap<Subject, Integer>() {{
                put(subjectPhysics, 4);
                put(subjectInformatics, 4);
                put(subjectChemistry, 4);
                put(subjectBiology, 5);
                put(subjectEnglish, 5);
                put(subjectHistory, 3);
                put(subjectRussian, 5);
                put(subjectLiterature, 3);
                put(subjectAlgebra, 5);
                put(subjectGeometry, 4);
                put(subjectGeography, 3);
            }}
        );
        final SchoolCertificate schoolCertificateNovikova = new SchoolCertificate(
            userNovikova,
            2017,
            new HashMap<Subject, Integer>() {{
                put(subjectPhysics, 4);
                put(subjectInformatics, 5);
                put(subjectChemistry, 3);
                put(subjectBiology, 4);
                put(subjectEnglish, 3);
                put(subjectHistory, 3);
                put(subjectRussian, 5);
                put(subjectLiterature, 3);
                put(subjectAlgebra, 3);
                put(subjectGeometry, 4);
                put(subjectGeography, 5);
            }}
        );
        final SchoolCertificate schoolCertificateMakarenko = new SchoolCertificate(
            userMakarenko,
            2017,
            new HashMap<Subject, Integer>() {{
                put(subjectPhysics, 5);
                put(subjectInformatics, 3);
                put(subjectChemistry, 4);
                put(subjectBiology, 3);
                put(subjectEnglish, 3);
                put(subjectHistory, 3);
                put(subjectRussian, 3);
                put(subjectLiterature, 4);
                put(subjectAlgebra, 4);
                put(subjectGeometry, 3);
                put(subjectGeography, 5);
            }}
        );
        final SchoolCertificate schoolCertificateKoshkin = new SchoolCertificate(
            userKoshkin,
            2017,
            new HashMap<Subject, Integer>() {{
                put(subjectPhysics, 5);
                put(subjectInformatics, 4);
                put(subjectChemistry, 4);
                put(subjectBiology, 4);
                put(subjectEnglish, 4);
                put(subjectHistory, 4);
                put(subjectRussian, 3);
                put(subjectLiterature, 4);
                put(subjectAlgebra, 3);
                put(subjectGeometry, 5);
                put(subjectGeography, 3);
            }}
        );

        final SchoolCertificateService schoolCertificateService = new SchoolCertificateService();
        schoolCertificateService.save(schoolCertificateIvanov);
        schoolCertificateService.save(schoolCertificateVolkova);
        schoolCertificateService.save(schoolCertificateAutlev);
        schoolCertificateService.save(schoolCertificateKanina);
        schoolCertificateService.save(schoolCertificateBorisenko);
        schoolCertificateService.save(schoolCertificateSkokova);
        schoolCertificateService.save(schoolCertificateSaprunov);
        schoolCertificateService.save(schoolCertificateNovikova);
        schoolCertificateService.save(schoolCertificateMakarenko);
        schoolCertificateService.save(schoolCertificateKoshkin);

        // init exam certificates
        final ExamCertificate examCertificateIvanov = new ExamCertificate(
            userService.get(userIvanov.getId()),
            2017,
            new HashMap<Subject, Integer>() {{
                put(subjectPhysics, 95);
                put(subjectChemistry, 75);
                put(subjectBiology, 91);
            }}
        );
        final ExamCertificate examCertificateVolkova = new ExamCertificate(
            userVolkova,
            2017,
            new HashMap<Subject, Integer>() {{
                put(subjectPhysics, 83);
                put(subjectInformatics, 96);
            }}
        );
        final ExamCertificate examCertificateAutlev = new ExamCertificate(
            userAutlev,
            2017,
            new HashMap<Subject, Integer>() {{
                put(subjectChemistry, 82);
                put(subjectBiology, 80);
            }}
        );
        final ExamCertificate examCertificateKanina = new ExamCertificate(
            userKanina,
            2017,
            new HashMap<Subject, Integer>() {{
                put(subjectEnglish, 88);
                put(subjectHistory, 87);
            }}
        );
        final ExamCertificate examCertificateBorisenko = new ExamCertificate(
            userBorisenko,
            2017,
            new HashMap<Subject, Integer>() {{
                put(subjectPhysics, 77);
                put(subjectInformatics, 96);
                put(subjectEnglish, 86);
            }}
        );
        final ExamCertificate examCertificateSkokova = new ExamCertificate(
            userSkokova,
            2017,
            new HashMap<Subject, Integer>() {{
                put(subjectPhysics, 96);
                put(subjectInformatics, 94);
            }}
        );
        final ExamCertificate examCertificateSaprunov = new ExamCertificate(
            userSaprunov,
            2017,
            new HashMap<Subject, Integer>() {{
                put(subjectEnglish, 95);
                put(subjectHistory, 75);
            }}
        );
        final ExamCertificate examCertificateNovikova = new ExamCertificate(
            userNovikova,
            2017,
            new HashMap<Subject, Integer>() {{
                put(subjectChemistry, 86);
                put(subjectBiology, 79);
            }}
        );
        final ExamCertificate examCertificateMakarenko = new ExamCertificate(
            userMakarenko,
            2017,
            new HashMap<Subject, Integer>() {{
                put(subjectChemistry, 83);
                put(subjectBiology, 97);
            }}
        );
        final ExamCertificate examCertificateKoshkin = new ExamCertificate(
            userKoshkin,
            2017,
            new HashMap<Subject, Integer>() {{
                put(subjectPhysics, 88);
                put(subjectInformatics, 96);
                put(subjectBiology, 85);
            }}
        );

        final ExamCertificateService examCertificateService = new ExamCertificateService();
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
            userService.get(userIvanov.getId()), facultyStomatology, 0, 0
        );
        final Sheet sheetVolkova = new Sheet(
            userService.get(userVolkova.getId()), facultyPhysical, 0, 0
        );
        final Sheet sheetAutlev = new Sheet(
            userService.get(userAutlev.getId()), facultyStomatology, 0, 0
        );
        final Sheet sheetKanina = new Sheet(
            userService.get(userKanina.getId()), facultyHistorical, 0, 0
        );
        final Sheet sheetBorisenko = new Sheet(
            userService.get(userBorisenko.getId()), facultyPhysical, 0, 0
        );
        final Sheet sheetSkokova = new Sheet(
            userService.get(userSkokova.getId()), facultyPhysical, 0, 0
        );
        final Sheet sheetSaprunov = new Sheet(
            userService.get(userSaprunov.getId()), facultyHistorical, 0, 0
        );
        final Sheet sheetNovikova = new Sheet(
            userService.get(userNovikova.getId()), facultyStomatology, 0, 0
        );
        final Sheet sheetMakarenko = new Sheet(
            userService.get(userMakarenko.getId()), facultyStomatology, 0, 0
        );
        final Sheet sheetKoshkin = new Sheet(
            userService.get(userKoshkin.getId()), facultyPhysical, 0, 0
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

//        final User userByMail = userService.getByMail("kda@epam.com");
//        final List<Sheet> byFaculty1 = sheetService.getByFaculty(facultyPhysical);
//        final List<Sheet> byFaculty2 = sheetService.getByFaculty(facultyStomatology);
//        final List<Sheet> byFaculty3 = sheetService.getByFaculty(facultyHistorical);
//        final User userByMail2 = userService.getByMail("kad@epam.com");
//        final User userByMail3 = userService.getByMail("kad@epam.com");

    }

}
