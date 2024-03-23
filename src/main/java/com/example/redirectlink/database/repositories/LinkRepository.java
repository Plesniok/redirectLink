package com.example.redirectlink.database.repositories;

import com.example.redirectlink.database.enities.LinkEnity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
public interface LinkRepository extends CrudRepository<LinkEnity, Long> {

}
