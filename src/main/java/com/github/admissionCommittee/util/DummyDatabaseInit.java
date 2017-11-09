package com.github.admissionCommittee.util;

import com.github.admissionCommittee.core.ExamCertificate;
import com.github.admissionCommittee.core.Faculty;
import com.github.admissionCommittee.core.SchoolCertificate;
import com.github.admissionCommittee.core.Subject;
import com.github.admissionCommittee.core.User;
import com.github.admissionCommittee.core.enums.SubjectNameEnum;
import com.github.admissionCommittee.core.enums.UserTypeEnum;
import com.github.admissionCommittee.dao.ExamCertificateDao;
import com.github.admissionCommittee.dao.FacultyDao;
import com.github.admissionCommittee.dao.SchoolCertificateDao;
import com.github.admissionCommittee.dao.SheetDao;
import com.github.admissionCommittee.dao.SubjectDao;
import com.github.admissionCommittee.dao.UserDao;

import java.util.HashMap;
import java.util.HashSet;

public class DummyDatabaseInit {

    public static void main(String[] args) {
        final DummyDatabaseInit instance = new DummyDatabaseInit();
        instance.setUp();
        HibernateUtil.getSessionFactory().close();
    }

    private void setUp() {

        UserDao userDao = new UserDao();

        final User user1 = new User(UserTypeEnum.USER, "Peter", "Ivanov", 20);
        final User admin1 = new User(UserTypeEnum.ADMIN, "Julia", "Semenova", 31);
        final User user2 = new User(UserTypeEnum.USER, "Maxim", "Kasyanov", 18);

        userDao.create(user1);
        userDao.create(admin1);
        userDao.create(user2);


        SubjectDao subjectDao = new SubjectDao();

        final Subject subject1 = new Subject(SubjectNameEnum.BIOLOGY);
        final Subject subject2 = new Subject(SubjectNameEnum.ALGEBRA);
        final Subject subject3 = new Subject(SubjectNameEnum.RUSSIAN);

        subjectDao.create(subject1);
        subjectDao.create(subject2);
        subjectDao.create(subject3);


        FacultyDao facultyDao = new FacultyDao();

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


        SchoolCertificateDao schoolCertificateDao = new SchoolCertificateDao();

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


        ExamCertificateDao examCertificateDao = new ExamCertificateDao();

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


        SheetDao sheetDao = new SheetDao();



    }

}
