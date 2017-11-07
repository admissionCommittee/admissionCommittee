package com.github.admissionCommittee.model;

import javax.persistence.*;
//@Cache(usage = CacheConcurrencyStrategy.)

@Entity
@Table(name = "users")/*, uniqueConstraints = {
        @UniqueConstraint(columnNames =
                "email", name = "users_unique_email")
})*/
public class User extends NamedEntity {

   /*
    @Column(name = "email", nullable = false, unique = true)
    @SafeHtml
    private String email;

    @Column(name = "password", nullable = false)
    @Length(min = 5)
    @SafeHtml
    private String password;

    @Column(name = "registered", columnDefinition = "timestamp default now()")
    private Date registered = new Date();*/

    //@Cache(usage = CacheConcurrencyStrategy)
//fetch = FetchType.LAZY?, cascade?
    @ManyToOne
    @JoinColumn(name = "role")
    private Role role;

    public User() {
    }

    public User(User user) {
        this(user.getName(), user.getRole());
    }

    public User(String name, Role role) {
        super(name);
        setRole(role);
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