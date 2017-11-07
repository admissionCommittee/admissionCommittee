package com.github.admissionCommittee.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User extends NamedEntity {

    private Role role;

    public User() { }

    public User(User user) {
        this(user.getName(), user.getRole());
    }

    public User(String name, Role role) {
        super(name);
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + getId() +
                ", name=" + name +
                ", role=" + role +
                +'}';
    }

}