package com.gfmnow.appointmentscheduling.responseDTO;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AppointmentResponseDTO {
	private Integer appointmentId;
	private String clientName;
	private String lawyerName;
	private LocalDateTime appointmentDateTime;
	private String status;

}
