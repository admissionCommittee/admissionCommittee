package com.github.admissionCommittee.model;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "faculties")
@AttributeOverride(name = "id", column = @Column(name = "faculties_id",
        nullable = false))
public class Faculty extends NamedEntity {

    @Column(name = "max_students")
    private int maxStudents;

    // TODO: map to RequiredSubject instance using Hibernate annotations
    private Map<String, Object> requiredSubjects;

    public Faculty(String name) {
        this(name, 0);
    }

    public Faculty(String name, int maxStudents) {
        super(name);
        this.maxStudents = maxStudents;
        this.requiredSubjects = new HashMap<>();
    }

    public int getMaxStudents() {
        return maxStudents;
    }

    public void setMaxStudents(int maxStudents) {
        this.maxStudents = maxStudents;
    }

    public Map<String, Object> getRequiredSubjects() {
        return requiredSubjects;
    }

    public void setRequiredSubjects(Map<String, Object> requiredSubjects) {
        this.requiredSubjects = requiredSubjects;
    }

}
