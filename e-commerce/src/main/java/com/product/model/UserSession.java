package com.product.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
public class UserSession {
	@Id
	private Integer userid;
	private String uuid;
	private LocalDateTime localdatetime;

}
