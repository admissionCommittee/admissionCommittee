package com.github.admissionCommittee.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
//@Cache(usage = CacheConcurrencyStrategy.)

@Entity
@Table(name = "roles")/*, uniqueConstraints = {
        @UniqueConstraint(columnNames =
                "role", name = "roles_unique_role")
})*/
public class Role extends NamedEntity {

    @OneToMany(mappedBy = "role")
    private Set<User> users = new HashSet<>();

    public Role() {
    }

    public Role(User role) {
        this(role.getName());
    }

    public Role(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + getId() +
                ", name=" + name +
                +'}';
    }
}