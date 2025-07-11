package com.gfmnow.appointmentscheduling.service;

import java.util.List;

import com.gfmnow.appointmentscheduling.requestDTO.AvailabilityRequestDTO;
import com.gfmnow.appointmentscheduling.responseDTO.AvailabilityResponseDTO;

import jakarta.validation.Valid;

public interface AvailabilityService {

	List<AvailabilityResponseDTO> getAvailabilityByLawyerId(Integer lawyerId);

	AvailabilityResponseDTO addAvailability(@Valid AvailabilityRequestDTO dto);

	List<AvailabilityResponseDTO> getAllAvailabilities() ;


}
