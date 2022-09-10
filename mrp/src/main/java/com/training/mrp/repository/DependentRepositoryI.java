package com.training.mrp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.training.mrp.model.Dependent;

@Repository
public interface DependentRepositoryI extends MongoRepository<Dependent, Integer>{

}
