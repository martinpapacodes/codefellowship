package com.martipap.codefellowship.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
public class ApplicationUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(
            name="following",
            joinColumns = {@JoinColumn(name="userWhoFollows")},
            inverseJoinColumns = {@JoinColumn(name="followed_user")}
    )

    public Set<ApplicationUser> usersIFollow;

    @ManyToMany(mappedBy = "usersIFollow")
    public Set<ApplicationUser> followers;

    @OneToMany(mappedBy = "applicationUser")
    List<Post> posts;

    public List<Post> getPosts() {
        return this.posts;
    }

    public String username;
    public String password;
    public String firstName;
    public String lastName;
    public String dateOfBirth;
    public String bio;



    public ApplicationUser() {

    }

    public ApplicationUser(String username, String password, String firstName, String lastName, String dateOfBirth, String bio) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.bio = bio;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getBio() {
        return bio;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
