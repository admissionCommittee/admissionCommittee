package com.github.admissionCommittee.model;

import javax.persistence.*;

import java.util.Set;

@Entity
@Table(name = "users")
@AttributeOverride(name = "id", column = @Column(name = "user_id",
        nullable = false))
public class User extends NamedEntity {

    @Column(name = "role")
    private Role role;
    @Column(name = "name")
    private String name;
    @Column(name = "patronymic")
    private String patronymic;
    @Column(name = "surname")
    private String surname;
    @Column(name = "passport")
    private String passport;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    @OneToMany(mappedBy = "user")
    @Column(name = "faculty")
    private Set<Faculty> faculty;
    @OneToOne(fetch = FetchType.EAGER)
    @Column(name = "school_certificate")
    private Certificate schoolCertificate;
    @OneToOne(fetch = FetchType.EAGER)
    @Column(name = "unified_Exam_Certificate")
    private Certificate unifiedExamCertificate;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<Faculty> getFaculty() {
        return faculty;
    }

    public void setFaculty(Set<Faculty> faculty) {
        this.faculty = faculty;
    }

    public Certificate getSchoolCertificate() {
        return schoolCertificate;
    }

    public void setSchoolCertificate(Certificate schoolCertificate) {
        this.schoolCertificate = schoolCertificate;
    }

    public Certificate getUnifiedExamCertificate() {
        return unifiedExamCertificate;
    }

    public void setUnifiedExamCertificate(Certificate unifiedExamCertificate) {
        this.unifiedExamCertificate = unifiedExamCertificate;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public User() {
    }

    public User(User user) {
        this(user.getName(), user.getRole());
    }

    public User(String name, Role role) {
        super(name);
        this.role = role;
    }
}