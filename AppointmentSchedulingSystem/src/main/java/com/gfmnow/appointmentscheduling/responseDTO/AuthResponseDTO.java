package com.gfmnow.appointmentscheduling.responseDTO;

import lombok.Data;

@Data
public class AuthResponseDTO {
	private String token;

	public AuthResponseDTO(String token) {
		super();
		this.token = token;
	}

}
