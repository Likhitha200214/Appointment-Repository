package com.gfmnow.appointmentscheduling.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gfmnow.appointmentscheduling.entity.Client;
import com.gfmnow.appointmentscheduling.repository.ClientRepository;
import com.gfmnow.appointmentscheduling.requestDTO.ClientRequestDTO;
import com.gfmnow.appointmentscheduling.responseDTO.ClientResponseDTO;
import com.gfmnow.appointmentscheduling.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	public ClientRepository clientRepository;

	@Override
	public ClientResponseDTO registerClient(ClientRequestDTO dto) {
		Client client = new Client();
		client.setName(dto.getName());
		client.setEmail(dto.getEmail());
		Client saved = clientRepository.save(client);

		ClientResponseDTO responseDto = new ClientResponseDTO();
		responseDto.setId(saved.getId());
		responseDto.setName(saved.getName());
		responseDto.setEmail(saved.getEmail());

		return responseDto;
	}

}
