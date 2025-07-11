package com.gfmnow.appointmentscheduling.responseDTO;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvailabilityResponseDTO {
	private Integer availabilityId;
	private LocalDate date;
	private LocalTime startTime;
	private LocalTime endTime;
	private String lawyerName;
}
