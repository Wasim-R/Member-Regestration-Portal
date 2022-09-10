package com.training.mrp.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.mrp.model.Dependent;
import com.training.mrp.model.Member;
import com.training.mrp.repository.DependentRepositoryI;
import com.training.mrp.repository.MemberRepositoryI;

@Service
public class MemberService implements MemberServiceI{
	
	@Autowired
	private MemberRepositoryI memberRepo;
	
	@Autowired
	private DependentRepositoryI dependentRepo;

	@Override
	public List<Member> getMembers() {
		return memberRepo.findAll();
	}

	@Override
	public Optional<Member> getMemberById(Integer id) {
		return memberRepo.findById(id);
	}

	@Override
	public Optional<Member> save(Member member) {
				
		if(member.getDependents().size() != 0) {
			for(Dependent dependent: member.getDependents()) {
				if(dependent.getId() == null) {
					dependent.setId(getLargestId("dependent")+1);
				}
			}
			
			dependentRepo.saveAll(member.getDependents());
		}
		
		if(member.getId() == null) {
			member.setId(getLargestId("member")+1);
		}
		
		
		
		memberRepo.save(member);
		
		return memberRepo.findById(member.getId());
	}

	@Override
	public Member checkLoginCredentials(String username, String password) {
		List<Member> members = memberRepo.findAll();
				
		List<Member> filteredMember = members.stream()
			.filter(member -> member.getEmail().equalsIgnoreCase(username) 
					&& member.getPassword().equals(password)).collect(Collectors.toList());
		
		if(!filteredMember.isEmpty()) {
			return filteredMember.get(0);
		}
		
		return null;
	}

	private Integer getLargestId(String repoName) {
		List<Integer> ids = new ArrayList<Integer>();

		if(repoName.equals("member")) {
			List<Member> members = memberRepo.findAll();
			for(Member member: members) {
				ids.add(member.getId());
			}
		}else {
			List<Dependent> dependents = dependentRepo.findAll();
			for(Dependent dependent: dependents) {
				ids.add(dependent.getId());
			}
		}
		
		return Collections.max(ids);
	}

}
