package com.martipap.codefellowship.models;

import javax.persistence.*;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    long id;

    @ManyToOne
    ApplicationUser applicationUser;

    String body;
    String createdAt;

    public Post() {

    }

    public Post(ApplicationUser application_user, String body, String createdAt) {
        this.applicationUser = application_user;
        this.body = body;
        this.createdAt = createdAt;
    }

    public String getBody() {
        return body;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}
