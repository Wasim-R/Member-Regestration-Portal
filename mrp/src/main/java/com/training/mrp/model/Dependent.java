package com.training.mrp.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Document(collection="dependent")
public class Dependent {
	
	@Id
	private Integer id;
	
	private String name;
	private String dateOfBirth;
	
}
