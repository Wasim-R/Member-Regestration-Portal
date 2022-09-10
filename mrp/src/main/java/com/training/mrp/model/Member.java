package com.training.mrp.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter

@Document(collection = "member")
public class Member {
	
	@Id
	private Integer id;
	
	private String name;
	private String dateOfBirth;
	private Integer age;
	private Long contactNumber;
	private String panNumber;
	private String email;
	private String password;
	private Address address;
	
	private List<Dependent> dependents;

}
