package com.training.mrp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.training.mrp.model.Member;

@Service
public interface MemberServiceI {
	
	public List<Member> getMembers();
	
	public Optional<Member> getMemberById(Integer Id);
	
	public Optional<Member> save(Member member);
	
	public Member checkLoginCredentials(String username, String password);
	
}
