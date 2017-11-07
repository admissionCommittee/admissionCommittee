package com.github.admissionCommittee.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "certificate_scores")
public class CertificateScore extends NamedEntity {

    private int scoreLimit;

    @Column(name = "score")
    private int score;

    public CertificateScore(String subject, int scoreLimit, int score) {
        super(subject);
        this.scoreLimit = scoreLimit;
        this.score = score;
    }

    public int getScoreLimit() {
        return scoreLimit;
    }

    public void setScoreLimit(int scoreLimit) {
        this.scoreLimit = scoreLimit;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

}
