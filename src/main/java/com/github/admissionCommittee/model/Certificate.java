package com.github.admissionCommittee.model;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "certificates")
@AttributeOverride(name = "id", column = @Column(name = "certificate_id",
        nullable = false))
@Deprecated
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
