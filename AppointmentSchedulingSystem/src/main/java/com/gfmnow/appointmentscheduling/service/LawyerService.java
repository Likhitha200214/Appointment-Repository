package com.gfmnow.appointmentscheduling.service;

import com.gfmnow.appointmentscheduling.requestDTO.LawyerRequestDTO;
import com.gfmnow.appointmentscheduling.responseDTO.LawyerResponseDTO;

public interface LawyerService {
	LawyerResponseDTO registerLawyer(LawyerRequestDTO dto);

}
