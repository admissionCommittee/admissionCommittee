package com.github.admissionCommittee.core;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Map;

@Entity
@Table(name = "school_certificate_id")
public class SchoolCertificate extends AbstractEntity {

    @OneToOne
    private User user;

    @Column(name = "year")
    private int year;

    @ElementCollection
    @CollectionTable(name = "subject_school_certificate",
            joinColumns = @JoinColumn(name = "school_certificate_id"))
    @MapKeyJoinColumn(name = "subject_id")
    @Column(name = "score")
    private Map<Subject, Integer> subjects;

    public SchoolCertificate() {
    }

    public SchoolCertificate(User user, int year, Map<Subject, Integer>
            subjects) {
        this.user = user;
        this.year = year;
        this.subjects = subjects;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Map<Subject, Integer> getSubjects() {
        return subjects;
    }

    public void setSubjects(Map<Subject, Integer> subjects) {
        this.subjects = subjects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final SchoolCertificate that = (SchoolCertificate) o;

        return year == that.year && user.equals(that.user) && subjects.equals
                (that.subjects);
    }

    @Override
    public int hashCode() {
        int result = user.hashCode();
        result = 31 * result + year;
        result = 31 * result + subjects.hashCode();
        return result;
    }
}
