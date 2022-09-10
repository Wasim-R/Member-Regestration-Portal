package com.training.mrp.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.training.mrp.exception.ClaimAlreadyExistException;
import com.training.mrp.exception.ResourceNotFoundException;
import com.training.mrp.model.Claim;
import com.training.mrp.model.Dependent;
import com.training.mrp.model.Member;
import com.training.mrp.repository.ClaimRepositoryI;
import com.training.mrp.repository.DependentRepositoryI;
import com.training.mrp.repository.MemberRepositoryI;

@Service
public class ClaimService implements ClaimServiceI{
	
	@Autowired
	private ClaimRepositoryI claimRepo;
	
	@Autowired
	private MemberRepositoryI memberRepo;
	
	@Autowired
	private DependentRepositoryI dependentRepo;

	@Override
	public boolean save(Claim claim) {
		
		if(claim.getId() == null) {
			claim.setId(getLargestId()+1);
		}
		
		claimRepo.save(claim);		
		return true;
	}
	
	@SuppressWarnings("null")
	@Override
	public ResponseEntity<?> getMemberById(Integer id) {
//		List<Integer> ids = new ArrayList<>();
		List<Integer> claimIds = new ArrayList<>();
		
//		List<Member> members = memberRepo.findAll();
		
		List<Claim> claims = claimRepo.findAll();
		
//		for(Member member: members) {
//			ids.add(member.getId());
//			for(Dependent dependent: member.getDependents()) {
//				ids.add(dependent.getId());
//			}
//		}
		
		for(Claim claim: claims) {
			claimIds.add(claim.getClaimMemberId());
		}
		
		List<Integer> filteredId = 
				claimIds.stream().filter(identity -> identity.equals(id)).collect(Collectors.toList());
		
		if(filteredId.size() > 0) {
			throw new ClaimAlreadyExistException("Claim with the id "+id+" already exist");
		}
		
		Optional<Member> member = memberRepo.findById(id);
		Optional<Dependent> dependent = null;
				
		if(member.isEmpty()) {
			dependent = dependentRepo.findById(id);
			if(dependent.isEmpty()) {
				throw new ResourceNotFoundException("Id not available");
			}
			return ResponseEntity.ok(dependent.get());
		} else {
			return ResponseEntity.ok(member);
		}
	}
	
	private Integer getLargestId() {
		List<Integer> ids = new ArrayList<Integer>();

			List<Claim> claims = claimRepo.findAll();
			for(Claim claim: claims) {
				ids.add(claim.getId());
			}
		
		return Collections.max(ids);
	}

}
