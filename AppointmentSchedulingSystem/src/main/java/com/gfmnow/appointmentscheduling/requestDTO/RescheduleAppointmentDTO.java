package com.gfmnow.appointmentscheduling.requestDTO;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RescheduleAppointmentDTO {

	@NotNull(message = "Appointment ID is required")
	private Integer appointmentId;

	@NotNull(message = "New appointment date/time is required")
	@Future(message = "Appointment date/time must be in the future")
	private LocalDateTime newAppointmentDateTime;


}
