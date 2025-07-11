package com.gfmnow.appointmentscheduling.requestDTO;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AvailabilityRequestDTO {

	@NotNull(message = "Lawyer ID is required")
	private Integer lawyerId;

	@NotNull(message = "Date is required")
	private LocalDate date;

	@NotNull(message = "Start time is required")
	private LocalTime startTime;

	@NotNull(message = "End time is required")
	private LocalTime endTime;

}
