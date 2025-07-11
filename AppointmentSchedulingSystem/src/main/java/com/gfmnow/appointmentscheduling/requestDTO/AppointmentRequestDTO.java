package com.gfmnow.appointmentscheduling.requestDTO;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AppointmentRequestDTO {

	@NotNull(message = "Client Id is required")
	private Integer clientId;

	@NotNull(message = "Lawyer ID is required")
	private Integer lawyerId;

	@NotNull(message = "Appointment date/time is required")
	@Future(message = "Appoinment date/time must be in the future")
	private LocalDateTime appointmentDateTime;

}
