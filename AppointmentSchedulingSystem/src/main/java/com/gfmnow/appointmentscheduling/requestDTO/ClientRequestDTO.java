package com.gfmnow.appointmentscheduling.requestDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ClientRequestDTO {

	@NotBlank(message = "Client name is required")
	private String name;

	@Email(message = "Valid email is required")
	private String email;
}
