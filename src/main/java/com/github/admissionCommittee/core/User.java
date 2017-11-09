package com.github.admissionCommittee.core;

import com.github.admissionCommittee.core.enums.UserTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;

@Data
@Entity
@NoArgsConstructor
@Table(name = "user")
@EqualsAndHashCode(callSuper = true, doNotUseGetters = true)
@AttributeOverride(name = "id", column = @Column(name = "user_id", nullable = false))
@ToString(exclude = {"sheet", "examCertificate", "schoolCertificate"})
public class User extends AbstractEntity {

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private Sheet sheet;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private ExamCertificate examCertificate;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private SchoolCertificate schoolCertificate;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private UserTypeEnum type;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "patronymic")
    private String patronymic;

    @Email
    @Column(name = "mail", nullable = false, unique = true)
    private String mail;

    @Column(name = "age")
    private int age;

    public User(UserTypeEnum type, String firstName, String lastName, String patronymic,
                String mail, int age) {
        this.type = type;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.mail = mail;
        this.age = age;
    }

}
