package com.github.admissionCommittee.core;

import com.github.admissionCommittee.core.enums.SubjectNameEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "subject")
public class Subject extends AbstractEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false, unique = true)
    private SubjectNameEnum name;

    public Subject() { }

    public Subject(SubjectNameEnum name) {
        this.name = name;
    }

    public SubjectNameEnum getName() {
        return name;
    }

    public void setName(SubjectNameEnum name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Subject subject = (Subject) o;

        return name == subject.name;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

}
