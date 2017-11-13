package com.github.admissionCommittee.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sheet")
@EqualsAndHashCode(callSuper = true, doNotUseGetters = true)
@AttributeOverride(name = "id", column = @Column(name = "sheet_id", nullable = false))
@ToString(exclude = "user")
public class Sheet extends AbstractEntity {

    @OneToOne
    @NonNull
    private User user;

    @OneToOne
    @NonNull
    private Faculty faculty;

    @Column(name = "sum_exam_certificate_score")
    private int sumExamCertificateScore;

    @Column(name = "average_school_certificate_score")
    private double averageSchoolCertificateScore;

}
