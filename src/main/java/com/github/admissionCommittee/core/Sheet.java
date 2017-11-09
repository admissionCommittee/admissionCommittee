package com.github.admissionCommittee.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sheet")
public class Sheet extends AbstractEntity {

    @OneToOne(fetch = FetchType.LAZY, targetEntity = User.class)
    private User user;

    @OneToOne(fetch = FetchType.LAZY, targetEntity = Faculty.class)
    private Faculty faculty;

    @Column(name = "average_exam_certificate_score")
    private int averageExamCertificateScore;

    @Column(name = "average_school_certificate_score")
    private int averageSchoolCertificateScore;

    public Sheet() {
    }

    public Sheet(User user, Faculty faculty, int averageExamCertificateScore,
                 int averageSchoolCertificateScore) {
        this.user = user;
        this.faculty = faculty;
        this.averageExamCertificateScore = averageExamCertificateScore;
        this.averageSchoolCertificateScore = averageSchoolCertificateScore;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public int getAverageExamCertificateScore() {
        return averageExamCertificateScore;
    }

    public void setAverageExamCertificateScore(int averageExamCertificateScore) {
        this.averageExamCertificateScore = averageExamCertificateScore;
    }

    public int getAverageSchoolCertificateScore() {
        return averageSchoolCertificateScore;
    }

    public void setAverageSchoolCertificateScore(int averageSchoolCertificateScore) {
        this.averageSchoolCertificateScore = averageSchoolCertificateScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Sheet sheet = (Sheet) o;

        return averageExamCertificateScore == sheet.averageExamCertificateScore
                && averageSchoolCertificateScore == sheet
                .averageSchoolCertificateScore
                && user.equals(sheet.user) && faculty.equals(sheet.faculty);
    }

    @Override
    public int hashCode() {
        int result = user.hashCode();
        result = 31 * result + faculty.hashCode();
        result = 31 * result + averageExamCertificateScore;
        result = 31 * result + averageSchoolCertificateScore;
        return result;
    }
}
