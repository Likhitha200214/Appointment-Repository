package com.gfmnow.appointmentscheduling.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gfmnow.appointmentscheduling.entity.Lawyer;
import com.gfmnow.appointmentscheduling.repository.LawyerRepository;
import com.gfmnow.appointmentscheduling.requestDTO.LawyerRequestDTO;
import com.gfmnow.appointmentscheduling.responseDTO.LawyerResponseDTO;
import com.gfmnow.appointmentscheduling.service.LawyerService;

@Service
public class LawyerServiceImpl implements LawyerService {

	@Autowired
	private LawyerRepository lawyerRepository;

	@Override
	public LawyerResponseDTO registerLawyer(LawyerRequestDTO dto) {
		Lawyer lawyer = new Lawyer();
		lawyer.setName(dto.getName());
		lawyer.setEmail(dto.getEmail());

		Lawyer saved = lawyerRepository.save(lawyer);

		LawyerResponseDTO response = new LawyerResponseDTO();
		response.setId(saved.getId());
		response.setName(saved.getName());
		response.setEmail(saved.getEmail());

		return response;
	}

}
