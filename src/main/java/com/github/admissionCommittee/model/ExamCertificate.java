package com.github.admissionCommittee.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
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

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "exam_certificate")
@EqualsAndHashCode(callSuper = true, doNotUseGetters = true)
@AttributeOverride(name = "id", column = @Column(name = "exam_certificate_id", nullable = false))
@ToString(callSuper = true,exclude = {"user"})
public class ExamCertificate extends AbstractEntity {

    @OneToOne
    @NonNull
    private User user;

    @Column(name = "year")
    private int year;

    @ElementCollection
    @CollectionTable(name = "subject_exam_certificate",
            joinColumns = @JoinColumn(name = "exam_certificate_id"))
    @MapKeyJoinColumn(name = "subject_id")
    @Column(name = "score", nullable = false)
    private Map<Subject, Integer> subjects;

}
