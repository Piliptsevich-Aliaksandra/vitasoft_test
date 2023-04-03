package com.shurapili.vitasoft_test.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Set;

@Entity
public class User {

    public User() {

    }

    public User(String username) {
        this.username = username;
    }

    @Id
    @GeneratedValue
    private Long id;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @OrderBy("creationTime")
    private List<Application> applications;

    //name
    //@NotNull(message = "Login shouldn't be empty")
    //@NotEmpty(message = "Login shouldn't be empty")
    //@Size(min=4, max=24, message = "Login should contain from 4 to 24 characters")
    //@Pattern(regexp = "[a-z]+", message = "Expected lowercase Latin letters")
    private String username;

    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public List<Application> getApplications() {
        return applications;
    }

    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }

    public void addApplication(Application applications) {
        this.applications.add(applications);
    }
}

