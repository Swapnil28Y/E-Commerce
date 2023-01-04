package com.product.model;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer productid;
	private String productname;
	private Integer productprice;
	private String brand;
	private Integer quantity;
	private String color;
	private String manufacturer;
	@OneToOne(cascade = CascadeType.ALL)
	private Category category;
}
