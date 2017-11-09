package com.github.admissionCommittee.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "faculty")
public class Faculty extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "people_limit")
    private int peopleLimit;

    @ManyToMany
    @JoinTable(name = "subject_faculty")
    private Set<Subject> subjects;

    public Faculty() {
    }

    public Faculty(String name, int peopleLimit, Set<Subject> subjects) {
        this.name = name;
        this.peopleLimit = peopleLimit;
        this.subjects = subjects;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPeopleLimit() {
        return peopleLimit;
    }

    public void setPeopleLimit(int peopleLimit) {
        this.peopleLimit = peopleLimit;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Faculty faculty = (Faculty) o;

        return peopleLimit == faculty.peopleLimit && name.equals(faculty.name)
                && subjects.equals(faculty.subjects);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + peopleLimit;
        result = 31 * result + subjects.hashCode();
        return result;
    }

}
