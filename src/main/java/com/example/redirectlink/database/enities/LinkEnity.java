package com.example.redirectlink.database.enities;

import com.example.redirectlink.models.LinkKey;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import static org.springframework.data.cassandra.core.cql.PrimaryKeyType.PARTITIONED;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import static org.springframework.data.cassandra.core.cql.PrimaryKeyType.PARTITIONED;

@Table("links2")
public class LinkEnity {
    @PrimaryKeyColumn(name = "link_id", type = PARTITIONED)
    private UUID linkId;

    @Column("link")
    @NotNull(message = "Link cannot be dsanull")
    @URL(message = "Invalid url format")
    private String link;

    @Column("base64_id")
    private transient String base64Id;

    @Column("initdate")
    private LocalDate initDate;

    public LinkEnity() {}

    public LinkEnity(
            final LinkKey key,
            final String link,
            final String base64Id
    ) {
        this.linkId = linkId;
        this.link = link;
        this.base64Id = base64Id;
    }

    public String getLink() {
        return link;
    }

    public UUID getLinkId() {
        return this.linkId;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setBase64Id(String base64Id) {
        this.base64Id = base64Id;
    }

    public void setLinkId(UUID linkId) {
        this.linkId = linkId;
    }

    public String getBase64Id() {
        return base64Id;
    }

    public LocalDate getInitDate() {
        return initDate;
    }

    public void setInitDate(LocalDate initDate) {
        this.initDate = initDate;
    }
}
