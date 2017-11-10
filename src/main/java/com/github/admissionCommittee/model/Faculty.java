package com.github.admissionCommittee.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "faculty")
@EqualsAndHashCode(callSuper = true, doNotUseGetters = true)
@AttributeOverride(name = "id", column = @Column(name = "faculty_id", nullable = false))
@ToString(exclude = {"users", "sheet"})
public class Faculty extends AbstractEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @NonNull
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "faculty")
    private Set<User> users = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "faculty")
    private Sheet sheet;

    @Column(name = "people_limit")
    private int peopleLimit;

    @NonNull
    @ManyToMany
    @JoinTable(name = "subject_faculty")
    private Set<Subject> subjects;

    public Faculty(String name, int peopleLimit, Set<Subject> subjects) {
        this.name = name;
        this.peopleLimit = peopleLimit;
        this.subjects = subjects;
    }

}
