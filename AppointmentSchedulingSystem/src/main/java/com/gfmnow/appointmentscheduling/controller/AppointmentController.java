package com.gfmnow.appointmentscheduling.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gfmnow.appointmentscheduling.entity.Appointment;
import com.gfmnow.appointmentscheduling.exception.GlobalResponse;
import com.gfmnow.appointmentscheduling.requestDTO.AppointmentRequestDTO;
import com.gfmnow.appointmentscheduling.requestDTO.RescheduleAppointmentDTO;
import com.gfmnow.appointmentscheduling.responseDTO.AppointmentResponseDTO;
import com.gfmnow.appointmentscheduling.service.AppointmentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;

	@PostMapping("/schedule")
	public ResponseEntity<GlobalResponse> scheduleAppointment(@Valid @RequestBody AppointmentRequestDTO dto) {
		Appointment saved = appointmentService.scheduleAppointment(dto);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new GlobalResponse(201, "Appointment scheduled successfully", LocalDateTime.now()));

	}

	@PutMapping("/reschedule")
	public ResponseEntity<GlobalResponse> rescheduleAppointment(@Valid @RequestBody RescheduleAppointmentDTO dto) {
		appointmentService.rescheduleAppointment(dto);
		return ResponseEntity.ok(new GlobalResponse(200, "Appointment rescheduled successfully", LocalDateTime.now()));
	}

	@DeleteMapping("/cancel/{appointmentId}")
	public ResponseEntity<GlobalResponse> cancelAppointment(@PathVariable Integer appointmentId) {
		appointmentService.cancelAppointment(appointmentId);
		return ResponseEntity.ok(new GlobalResponse(200, "Appointment cancelled successfully", LocalDateTime.now()));
	}

	@GetMapping("/lawyer/{lawyerId}")
	public ResponseEntity<List<AppointmentResponseDTO>> getLawyerAppointments(@PathVariable Integer lawyerId) {
		List<AppointmentResponseDTO> appointments = appointmentService.getAppointmentsByLawyerId(lawyerId);
		return ResponseEntity.ok(appointments);
	}
	
	@GetMapping("/client/{clientId}")
	public ResponseEntity<List<AppointmentResponseDTO>> getClientAppointments(@PathVariable Integer clientId) {
	    List<AppointmentResponseDTO> appointments = appointmentService.getAppointmentsByClientId(clientId);
	    return ResponseEntity.ok(appointments);
	}


}
