package com.gfmnow.appointmentscheduling.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gfmnow.appointmentscheduling.requestDTO.AvailabilityRequestDTO;
import com.gfmnow.appointmentscheduling.responseDTO.AvailabilityResponseDTO;
import com.gfmnow.appointmentscheduling.service.AvailabilityService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/availability")
public class AvailabilityController {

	@Autowired
	private AvailabilityService availabilityService;

	@PostMapping("/add/avilability")
	public ResponseEntity<AvailabilityResponseDTO> addAvailability(@RequestBody @Valid AvailabilityRequestDTO dto) {
		AvailabilityResponseDTO responseDTO = availabilityService.addAvailability(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
	}

	@GetMapping("/getAll/availabilities")
	public ResponseEntity<List<AvailabilityResponseDTO>> getAllAvailabilities() {
		return ResponseEntity.ok(availabilityService.getAllAvailabilities());
	}

}
