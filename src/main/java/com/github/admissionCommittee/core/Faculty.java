package com.github.admissionCommittee.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "faculty")
@EqualsAndHashCode(callSuper = true, doNotUseGetters = true)
@AttributeOverride(name = "id", column = @Column(name = "faculty_id", nullable = false))
public class Faculty extends AbstractEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "people_limit")
    private int peopleLimit;

    @ManyToMany
    @JoinTable(name = "subject_faculty")
    @NonNull
    private Set<Subject> subjects;

}
