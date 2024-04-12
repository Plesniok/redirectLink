package com.example.redirectlink.database.repositories;

import com.example.redirectlink.database.enities.LinkEnity;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
@Repository
public interface LinkRepository extends CassandraRepository<LinkEnity, Long> {

}
