package com.training.mrp.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.mrp.exception.DataMissingException;
import com.training.mrp.exception.GlobalExceptionHandler;
import com.training.mrp.exception.ResourceNotFoundException;
import com.training.mrp.model.Address;
import com.training.mrp.model.Login;
import com.training.mrp.model.Member;
import com.training.mrp.repository.MemberRepositoryI;
import com.training.mrp.service.MemberServiceI;

@RestController
@RequestMapping("/api/member")
public class MemberController {

	@Autowired
	private MemberServiceI memberService;

	@PostMapping("/register")
	public ResponseEntity<?> CreateMember(@RequestBody Member member) {

//		if (member.getEmail().equals(null) || 
//				member.getPassword().equals(null) || member.getDateOfBirth().equals(null)
//				|| member.getName().equals(null)) {
//			throw new DataMissingException("Mandatory data is missing");
//		}
		
		Optional<Member> memberDetail = memberService.save(member);
		return ResponseEntity.ok(memberDetail);
	}

	@GetMapping("/find/{id}")
	public Optional<Member> getMemberById(@PathVariable(value = "id") Integer id) {
		Optional<Member> member = memberService.getMemberById(id);

		if (member.isEmpty()) {
			throw new ResourceNotFoundException("Member not found with the id : " + id);
		}

		return member;
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateMember(@PathVariable(value = "id") Integer id, @RequestBody Member member) {
		Optional<Member> existingMember = memberService.getMemberById(id);

		if (existingMember.isEmpty()) {
			throw new ResourceNotFoundException("Member not found with the id : " + id);
		}
		
//		if (member.getEmail().isEmpty() || member.getPassword().isEmpty() || member.getDateOfBirth().isEmpty()
//				|| member.getName().isEmpty() || member.getContactNumber().toString().isEmpty()) {
//			throw new DataMissingException("Mandatory data is missing");
//		}

		Optional<Member> updatedMember = memberService.save(member);
		return ResponseEntity.ok(updatedMember.get());
	}

	@PostMapping("/login")
	public ResponseEntity<?> loginCheck(@RequestBody Login loginCredential) {
		Member member = memberService.checkLoginCredentials(loginCredential.getUsername(), loginCredential.getPassword());
		
		if(member == null) {
			throw new ResourceNotFoundException("Invalid Credentials");
		}
		
		return ResponseEntity.ok(member);
	}

}
