package com.github.admissionCommittee.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.AttributeOverride;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Map;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "school_certificate_id")
@EqualsAndHashCode(doNotUseGetters = true,exclude = {"user"})
@AttributeOverride(name = "id", column = @Column(name = "school_certificate_id", nullable = false))
@ToString(callSuper = true,exclude = {"user"})
public class SchoolCertificate extends AbstractEntity {

    @OneToOne
    @NonNull
    private User user;

    @Column(name = "year")
    private int year;

    @Column(name = "average_school_certificate_score")
    private double averageScore;

    @ElementCollection
    @CollectionTable(name = "subject_school_certificate",
            joinColumns = @JoinColumn(name = "school_certificate_id"))
    @MapKeyJoinColumn(name = "subject_id")
    @Column(name = "score", nullable = false)
    private Map<Subject, Integer> subjects;

    public SchoolCertificate(User user, int year, Map<Subject, Integer> subjects) {
        this.user = user;
        this.year = year;
        this.subjects = subjects;
    }
}
