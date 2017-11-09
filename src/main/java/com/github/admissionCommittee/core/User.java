package com.github.admissionCommittee.core;

import com.github.admissionCommittee.core.enums.UserTypeEnum;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User extends AbstractEntity {

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Sheet sheet;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private ExamCertificate examCertificate;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private SchoolCertificate schoolCertificate;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private UserTypeEnum type;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "age")
    private int age;

    public User() { }

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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final User user = (User) o;

        return age == user.age && (sheet != null ? sheet.equals(user.sheet) : user.sheet == null)
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
        result = 31 * result + (examCertificate != null ? examCertificate.hashCode() : 0);
        result = 31 * result + (schoolCertificate != null ? schoolCertificate.hashCode() : 0);
        result = 31 * result + type.hashCode();
        result = 31 * result + firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + age;
        return result;
    }

}
