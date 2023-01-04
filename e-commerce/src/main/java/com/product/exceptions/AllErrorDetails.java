package com.product.exceptions;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AllErrorDetails {
	 private LocalDateTime timestamp;
	    private String message;
	    private String details;
}
