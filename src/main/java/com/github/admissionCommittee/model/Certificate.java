package com.github.admissionCommittee.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "certificates")
public class Certificate extends NamedEntity {

    private Map<String, CertificateScore> scores;

    @Column(name = "graduation_date")
    @Temporal(TemporalType.DATE)
    private Date graduationDate;
    @Column(name = "institution")
    private String institution;
    @Column(name = "uid")
    private int uid;

    public Certificate(String name) {
        super(name);
        scores = new HashMap<>();
    }

    public Map<String, CertificateScore> getScores() {
        return scores;
    }

    public void setScores(Map<String, CertificateScore> scores) {
        this.scores = scores;
    }

    public Date getGraduationDate() {
        return graduationDate;
    }

    public void setGraduationDate(Date graduationDate) {
        this.graduationDate = graduationDate;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

}
