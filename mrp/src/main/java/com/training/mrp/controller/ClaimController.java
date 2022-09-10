package com.training.mrp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.training.mrp.exception.DataMissingException;
import com.training.mrp.model.Claim;
import com.training.mrp.service.ClaimServiceI;

@RestController
public class ClaimController {
	
	@Autowired
	private ClaimServiceI claimService;
	
	@PostMapping("/api/submitClaim")
	public ResponseEntity<?> createClaim(@RequestBody Claim claim) {
		
//		if (claim.getName().isEmpty() || claim.getBillAmmount().isEmpty()) {
//			throw new DataMissingException("Mandatory data is missing");
//		}
		
		boolean claimStatus = claimService.save(claim);
		
		if(claimStatus) {
			return ResponseEntity.ok("Claim successfully created");
		} else {
			return (ResponseEntity<?>) ResponseEntity.badRequest();
		}
	}	
	
	@GetMapping("/api/checkMember/{id}")
	public ResponseEntity<?> getMemberById(@PathVariable(value = "id") Integer id){
		return claimService.getMemberById(id);
	}
	
}
