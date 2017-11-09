package com.github.admissionCommittee.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "faculty")
@AttributeOverride(name = "id", column = @Column(name = "faculty_id",
        nullable = false))
public class Faculty extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "faculty")
    private Set<User> users =new HashSet<User>();

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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
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

    @Override
    public String toString() {
        return "Faculty{" +
                "name='" + name + '\'' +
                ", users=" + users +
                ", peopleLimit=" + peopleLimit +
                ", subjects=" + subjects +
                '}';
    }
}
