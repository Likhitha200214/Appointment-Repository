package com.gfmnow.appointmentscheduling.service;

import com.gfmnow.appointmentscheduling.requestDTO.ClientRequestDTO;
import com.gfmnow.appointmentscheduling.responseDTO.ClientResponseDTO;

public interface ClientService {
	
	ClientResponseDTO registerClient(ClientRequestDTO dto);
	

}
