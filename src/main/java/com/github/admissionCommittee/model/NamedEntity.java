package com.github.admissionCommittee.model;

import com.sun.xml.internal.bind.v2.TODO;
import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;


@MappedSuperclass
@Deprecated
public class NamedEntity extends BaseEntity {
//TODO link to hashcode obligatory link
    @Column(name = "name", nullable = false)
    @SafeHtml
    protected String name;

    public NamedEntity() {
    }

    protected NamedEntity(String name) {
        super();
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return String.format("Entity %s (%s, '%s')", getClass().getName(), getId(), name);
    }
}
