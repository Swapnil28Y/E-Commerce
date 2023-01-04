package com.product.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
	private String buidingname;

	private String streetNo;

	private String city;

	private String state;

	private String country;

	private String pincode;
}
