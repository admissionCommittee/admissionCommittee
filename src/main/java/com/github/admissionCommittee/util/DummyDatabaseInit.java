package com.github.admissionCommittee.util;

import com.github.admissionCommittee.dao.DaoFactory;
import com.github.admissionCommittee.model.ExamCertificate;
import com.github.admissionCommittee.model.Faculty;
import com.github.admissionCommittee.model.SchoolCertificate;
import com.github.admissionCommittee.model.Sheet;
import com.github.admissionCommittee.model.Subject;
import com.github.admissionCommittee.model.User;
import com.github.admissionCommittee.model.enums.SubjectNameEnum;
import com.github.admissionCommittee.model.enums.UserTypeEnum;
import com.github.admissionCommittee.dao.ExamCertificateDao;
import com.github.admissionCommittee.dao.FacultyDao;
import com.github.admissionCommittee.dao.SchoolCertificateDao;
import com.github.admissionCommittee.dao.SheetDao;
import com.github.admissionCommittee.dao.SubjectDao;
import com.github.admissionCommittee.dao.UserDao;
import com.github.admissionCommittee.service.UserService;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.HashSet;

public class DummyDatabaseInit {

    public static void main(String[] args) {
        final DummyDatabaseInit instance = new DummyDatabaseInit();
        instance.setUp();
        //HibernateUtil.getSessionFactory().close();
    }

    private void setUp() {

        UserDao userDao = DaoFactory.getDaoFactory().getUserDao();

        final User user1 = new User(UserTypeEnum.USER, "Иванов", "Пётр", "Сергеевич",
                "ivanov_piotr@epam.com", "qwerty", LocalDate.of(1992, Month.AUGUST, 12));
        final User user3 = new User(UserTypeEnum.USER, "Петров", "Пётр", "Сергеевич",
                "user@epam.com", "123", LocalDate.of(1990, Month.APRIL, 2));
        final User admin1 = new User(UserTypeEnum.ADMIN, "Семёнова", "Юлия", "Викторовна",
                "iuliia_semionova@epam.com", "123456", LocalDate.of(1985, Month.DECEMBER, 22));
        final User user2 = new User(UserTypeEnum.USER, "Касьянов", "Максим", "Евгеньевич",
                "kasianov_maksim@epam.com", "PaSsWoRd", LocalDate.of(1997, Month.FEBRUARY, 17));

        userDao.create(user1);
        userDao.create(admin1);
        userDao.create(user2);
        userDao.create(user3);


        SubjectDao subjectDao = DaoFactory.getDaoFactory().getSubjectDao();

        final Subject subject1 = new Subject(SubjectNameEnum.BIOLOGY);
        final Subject subject2 = new Subject(SubjectNameEnum.ALGEBRA);
        final Subject subject3 = new Subject(SubjectNameEnum.RUSSIAN);

        subjectDao.create(subject1);
        subjectDao.create(subject2);
        subjectDao.create(subject3);


        FacultyDao facultyDao = DaoFactory.getDaoFactory().getFacultyDao();

        final HashSet<Subject> faculty1Subjects = new HashSet<>();
        faculty1Subjects.add(subject1);
        final Faculty faculty1 = new Faculty("faculty1", 61, faculty1Subjects);

        final HashSet<Subject> faculty2Subjects = new HashSet<>();
        faculty2Subjects.add(subject1);
        faculty2Subjects.add(subject3);
        final Faculty faculty2 = new Faculty("faculty2", 104, faculty2Subjects);

        final HashSet<Subject> faculty3Subjects = new HashSet<>();
        faculty2Subjects.add(subject2);
        faculty2Subjects.add(subject3);
        final Faculty faculty3 = new Faculty("faculty3", 85, faculty3Subjects);

        facultyDao.create(faculty1);
        facultyDao.create(faculty2);
        facultyDao.create(faculty3);


        SchoolCertificateDao schoolCertificateDao = DaoFactory.getDaoFactory()
                .getSchoolCertificateDao();

        final HashMap<Subject, Integer> sc1Subjects = new HashMap<>();
        sc1Subjects.put(subject1, 5);
        sc1Subjects.put(subject3, 4);
        final SchoolCertificate schoolCertificate1 =
                new SchoolCertificate(user1, 2017, sc1Subjects);

        final HashMap<Subject, Integer> sc2Subjects = new HashMap<>();
        sc2Subjects.put(subject2, 4);
        sc2Subjects.put(subject3, 3);
        final SchoolCertificate schoolCertificate2 =
                new SchoolCertificate(user2, 2016, sc2Subjects);

        schoolCertificateDao.create(schoolCertificate1);
        schoolCertificateDao.create(schoolCertificate2);


        ExamCertificateDao examCertificateDao = DaoFactory.getDaoFactory().getExamCertificateDao();

        final HashMap<Subject, Integer> ec1Subjects = new HashMap<>();
        ec1Subjects.put(subject1, 87);
        ec1Subjects.put(subject3, 92);
        final ExamCertificate examCertificate1 = new ExamCertificate(user1, 2017, ec1Subjects);

        final HashMap<Subject, Integer> ec2Subjects = new HashMap<>();
        ec2Subjects.put(subject2, 65);
        ec2Subjects.put(subject3, 73);
        final ExamCertificate examCertificate2 = new ExamCertificate(user2, 2017, ec2Subjects);

        examCertificateDao.create(examCertificate1);
        examCertificateDao.create(examCertificate2);


        SheetDao sheetDao = DaoFactory.getDaoFactory().getSheetDao();
        final Sheet sheet = new Sheet(user2, faculty1, 0, 0);
        sheetDao.create(sheet);

        final User byMail = userDao.getByMail("kasianov_maksim@epam.com");

        UserService userService = new UserService();

        User user = userService.getByMail("user@epam.com");
        System.out.println("!! " + user.getPassword() + " !!!!!!!!!!!!!!! ");

    }

}
