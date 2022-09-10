package com.training.mrp;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.annotation.Order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.test.context.junit4.SpringRunner;

import com.training.mrp.model.Address;
import com.training.mrp.model.Member;
import com.training.mrp.repository.MemberRepositoryI;
import com.training.mrp.service.MemberServiceI;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class MrpApplicationTests {
	
	@Autowired
	private MemberServiceI memberService;
	
	@MockBean
	private MemberRepositoryI memberRepository; 
	
	
	@Test
	@Order(1)
	public void createMemberTest() {
		Member member = new Member();
		Address address = new Address();
		member.setEmail("haritharaj@gmail.com");
		member.setName("Haritha");
		member.setDateOfBirth("18/03/1997");
		member.setContactNumber(9094828327l);
		member.setPanNumber("ATAPH5667L");
		member.setPassword("Abc@123");
		address.setAddressLine("abc street");
		address.setCity("chennai");
		address.setCountry("India");
		address.setPincode(600123);
		address.setState("Tamil Nadu");
		member.setAddress(address);
		member.setId(123);
	
		when(memberRepository.save(member)).thenReturn(member);
						
//		.thenReturn(Stream.of(member).collect(Collectors.toList()));
		
		assertNotNull(memberService.getMemberById(123));	
		
	}

}
