package com.github.admissionCommittee.model;

import javax.persistence.*;

@Entity
@Table(name = "user")
@AttributeOverride(name = "id", column = @Column(name = "user_id",
        nullable = false))
public class User extends AbstractEntity {

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Sheet sheet;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private ExamCertificate examCertificate;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private SchoolCertificate schoolCertificate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "faculty_id", nullable = false)
    public Faculty faculty;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private UserTypeEnum type;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "mail")
    private String mail;

    @Column(name = "age")
    private int age;

    public Sheet getSheet() {
        return sheet;
    }

    public void setSheet(Sheet sheet) {
        this.sheet = sheet;
    }

    public ExamCertificate getExamCertificate() {
        return examCertificate;
    }

    public void setExamCertificate(ExamCertificate examCertificate) {
        this.examCertificate = examCertificate;
    }

    public SchoolCertificate getSchoolCertificate() {
        return schoolCertificate;
    }

    public void setSchoolCertificate(SchoolCertificate schoolCertificate) {
        this.schoolCertificate = schoolCertificate;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public User() {
    }

    public User(UserTypeEnum type, String firstName, String lastName, int age) {
        this.type = type;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public UserTypeEnum getType() {
        return type;
    }

    public void setType(UserTypeEnum type) {
        this.type = type;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final User user = (User) o;

        return age == user.age && (sheet != null ? sheet.equals(user.sheet) :
                user.sheet == null)
                && (examCertificate != null
                ? examCertificate.equals(user.examCertificate)
                : user.examCertificate == null)
                && (schoolCertificate != null
                ? schoolCertificate.equals(user.schoolCertificate)
                : user.schoolCertificate == null)
                && type == user.type && firstName.equals(user.firstName)
                && lastName.equals(user.lastName);
    }

    @Override
    public int hashCode() {
        int result = sheet != null ? sheet.hashCode() : 0;
        result = 31 * result + (examCertificate != null ? examCertificate
                .hashCode() : 0);
        result = 31 * result + (schoolCertificate != null ? schoolCertificate
                .hashCode() : 0);
        result = 31 * result + type.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + age;
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "sheet=" + sheet +
                ", examCertificate=" + examCertificate +
                ", schoolCertificate=" + schoolCertificate +
                ", faculty=" + faculty +
                ", type=" + type +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", mail='" + mail + '\'' +
                ", age=" + age +
                '}';
    }
}
