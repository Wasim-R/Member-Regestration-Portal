package com.training.mrp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.training.mrp.model.Claim;

public interface ClaimRepositoryI extends MongoRepository<Claim, Integer> {

}
