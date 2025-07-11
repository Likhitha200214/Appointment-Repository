package com.gfmnow.appointmentscheduling.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gfmnow.appointmentscheduling.requestDTO.LawyerRequestDTO;
import com.gfmnow.appointmentscheduling.responseDTO.AvailabilityResponseDTO;
import com.gfmnow.appointmentscheduling.responseDTO.LawyerResponseDTO;
import com.gfmnow.appointmentscheduling.service.AvailabilityService;
import com.gfmnow.appointmentscheduling.service.LawyerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/lawyer")
public class LawyerController {

	@Autowired
	private LawyerService lawyerService;

	@Autowired
	private AvailabilityService availabilityService;

	@PostMapping("/register")
	public ResponseEntity<LawyerResponseDTO> register(@Valid @RequestBody LawyerRequestDTO dto) {
		return new ResponseEntity<>(lawyerService.registerLawyer(dto), HttpStatus.CREATED);
	}

	@GetMapping("/{lawyerId}/availabilities")
	public ResponseEntity<List<AvailabilityResponseDTO>> getAvailabilies(@PathVariable Integer lawyerId) {
		return ResponseEntity.ok(availabilityService.getAvailabilityByLawyerId(lawyerId));

	}

}
