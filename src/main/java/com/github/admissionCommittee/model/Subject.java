package com.github.admissionCommittee.model;

import com.github.admissionCommittee.model.enums.SubjectNameEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "subject")
@EqualsAndHashCode(doNotUseGetters = true)
@AttributeOverride(name = "id", column = @Column(name = "subject_id", nullable = false))
@ToString(callSuper = true)
public class Subject extends AbstractEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false, unique = true)
    private SubjectNameEnum name;

}
