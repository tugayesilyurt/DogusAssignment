package com.dogus.assisgnment.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "numbers")
public class Numbers{

	  @Id
	  private String id;

	  @Indexed(unique = true)
	  private int number;

	  private Date createdDate; 
	  private Date updatedDate; 


}
