package com.csc3402.project.librarymanagement.entity.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "role")
public class Role extends BaseEntity {

    private String user_role;

    @NotBlank
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users ;

    @Singular
    @ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST},fetch = FetchType.EAGER)
    @JoinTable(name = "role_authority",joinColumns = {@JoinColumn(name = "ROLE_ID",referencedColumnName = "ID")},inverseJoinColumns = {
            @JoinColumn(name = "AUTHORITY_ID",referencedColumnName = "ID")
    })

    private Set<Authority> authorities ;

    public String getUser_role() {
        return user_role;
    }

    public void setUser_role(String user_role) {
        this.user_role = user_role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }
}
