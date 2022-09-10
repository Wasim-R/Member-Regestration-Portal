package com.training.mrp.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.training.mrp.model.Claim;

@Service
public interface ClaimServiceI {
	
	public boolean save(Claim claim);
	
	public ResponseEntity<?> getMemberById(Integer id);
}
