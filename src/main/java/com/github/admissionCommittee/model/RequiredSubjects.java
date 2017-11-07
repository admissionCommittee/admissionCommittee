package com.github.admissionCommittee.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "requiredSubjects")
public class RequiredSubjects {
    @Column (name="subject_name")
    String subject;
}
