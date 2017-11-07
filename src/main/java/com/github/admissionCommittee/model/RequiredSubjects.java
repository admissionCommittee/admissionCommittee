package com.github.admissionCommittee.model;

<<<<<<< HEAD
import javax.persistence.AttributeOverride;
=======
>>>>>>> d81fe14cd47de4c3965e3f33a6dc8fc7cdcc0db2
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
<<<<<<< HEAD
@Table(name = "required_subjects")
@AttributeOverride(name = "id", column = @Column(name = "required_subjects_id",
        nullable = false))
=======
@Table(name = "requiredSubjects")
>>>>>>> d81fe14cd47de4c3965e3f33a6dc8fc7cdcc0db2
public class RequiredSubjects {
    @Column (name="subject_name")
    String subject;
}
