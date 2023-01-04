package com.product.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer customerid;
	@Size(min = 3, max = 12, message = "firstname should contain atleast 3 letters and atmost 12 letter")
	private String firstname;
	@Size(min = 3, max = 12, message = "lastname should contain atleast 3 letters and atmost 12 letter")
	private String lastname;
	@Min(value = 12, message = "age should be greater than 12")
	@Max(value = 120, message = "age should not be greater than 120")
	private Integer age;
	private String gender;
	@Size(min = 10, max = 10, message = "Mobilenumber should contain 10 digit strictly")
	private String mobilenumber;
	@Email(message = "Email should be valid")
	private String email;
	@Size(min = 8, max = 12, message = "Password should contain atleast 8 letters and atmost 12 letters")
	private String password;
	@OneToMany
	private List<Address> address = new ArrayList<>();
}
