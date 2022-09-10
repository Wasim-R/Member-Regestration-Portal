package com.training.mrp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.training.mrp.model.Member;

@Repository
public interface MemberRepositoryI extends MongoRepository<Member, Integer>{

}
