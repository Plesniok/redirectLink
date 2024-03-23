package com.example.redirectlink.database.enities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;

@Entity
@Table(name = "links")
public class LinkEnity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Link cannot be null")
    @URL(message = "Invalid url format")
    private String link;

    public LinkEnity() {}

    public LinkEnity(
            String link
    ) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public Long getId() {
        return this.id;
    }

    public void setLink(String link) {
        this.link = link;
    }

}
