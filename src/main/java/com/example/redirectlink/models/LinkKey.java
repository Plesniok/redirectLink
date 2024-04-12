package com.example.redirectlink.models;

import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

import java.io.Serializable;

import static org.springframework.data.cassandra.core.cql.PrimaryKeyType.PARTITIONED;

@PrimaryKeyClass
public class LinkKey implements Serializable {

    @PrimaryKeyColumn(name = "link_id", type = PARTITIONED)
    private int linkId;


    public LinkKey(final int linkId) {
        this.linkId = linkId;
    }

    // getters and setters

    // equals and hashcode
}
