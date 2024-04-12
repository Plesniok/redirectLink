package com.example.redirectlink.database.enities;

import com.example.redirectlink.models.LinkKey;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("links")
public class LinkEnity {
    @PrimaryKey
    private LinkKey key;

    @Column("link")
    @NotNull(message = "Link cannot be dsanull")
    @URL(message = "Invalid url format")
    private String link;

    @Column("base64_id")
    private transient String base64Id;

    public LinkEnity() {}

    public LinkEnity(
            final LinkKey key,
            final String link,
            final String base64Id
    ) {
        this.key = key;
        this.link = link;
        this.base64Id = base64Id;
    }

    public String getLink() {
        return link;
    }

    public LinkKey getKey() {
        return this.key;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setBase64Id(String base64Id) {
        this.base64Id = base64Id;
    }

    public void setKey(LinkKey key) {
        this.key = key;
    }

    public String getBase64Id() {
        return base64Id;
    }
}
