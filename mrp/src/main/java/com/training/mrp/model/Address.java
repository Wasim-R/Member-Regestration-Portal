package com.training.mrp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {
	
	@Id
	private Integer id;

	private String addressLine; 
	private String country;
	private String state;
	private String city;
	private Integer pincode;
}
