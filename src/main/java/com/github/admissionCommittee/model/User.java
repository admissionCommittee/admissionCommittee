package com.github.admissionCommittee.model;

import com.github.admissionCommittee.model.enums.UserAttendeeState;
import com.github.admissionCommittee.model.enums.UserTypeEnum;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Table(name = "user")
@Getter
@Setter
@EqualsAndHashCode(doNotUseGetters = true)
@AttributeOverride(name = "id", column = @Column(name = "user_id", nullable
        = false))
@ToString(callSuper = true)
public class User extends AbstractEntity {

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy =
            "user")
    private ExamCertificate examCertificate;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy =
            "user")
    private SchoolCertificate schoolCertificate;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
    private Sheet sheet;

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

    @Enumerated(EnumType.STRING)
    @Column(name = "userRole", nullable = false)
    private UserTypeEnum userRole;

    @Enumerated(EnumType.STRING)
    @Column(name = "userAttendeeStatus", nullable = false)
    private UserAttendeeState userAttendeeState;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "mail", nullable = false, unique = true)
    private String mail;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    public User(UserAttendeeState userAttendeeState, UserTypeEnum userRole,
                String lastName,
                String firstName,
                String patronymic,
                String mail, String password, LocalDate birthDate) {
        this.userRole = userRole;
        this.userAttendeeState = userAttendeeState;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.mail = mail;
        this.password = password;
        this.birthDate = birthDate;
    }
}