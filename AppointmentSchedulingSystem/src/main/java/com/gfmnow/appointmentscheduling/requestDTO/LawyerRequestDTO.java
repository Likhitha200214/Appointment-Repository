package com.gfmnow.appointmentscheduling.requestDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LawyerRequestDTO {

	@NotBlank(message = "Lawyer name is required")
	private String name;

	@Email(message = "Valid email is required")
	private String email;
}