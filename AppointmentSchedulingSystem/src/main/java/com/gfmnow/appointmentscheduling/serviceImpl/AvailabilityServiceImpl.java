package com.gfmnow.appointmentscheduling.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gfmnow.appointmentscheduling.entity.Availability;
import com.gfmnow.appointmentscheduling.entity.Lawyer;
import com.gfmnow.appointmentscheduling.exception.DataNotFound;
import com.gfmnow.appointmentscheduling.repository.AvailabilityRepository;
import com.gfmnow.appointmentscheduling.repository.LawyerRepository;
import com.gfmnow.appointmentscheduling.requestDTO.AvailabilityRequestDTO;
import com.gfmnow.appointmentscheduling.responseDTO.AvailabilityResponseDTO;
import com.gfmnow.appointmentscheduling.service.AvailabilityService;

import jakarta.validation.Valid;

@Service
public class AvailabilityServiceImpl implements AvailabilityService {

	@Autowired
	private AvailabilityRepository availabilityRepository;

	@Autowired
	private LawyerRepository lawyerRepository;

	public List<AvailabilityResponseDTO> getAvailabilityByLawyerId(Integer lawyerId) {
		// System.out.println("Fetching availabilities for lawyer ID:" + lawyerId);
		List<Availability> availabilities = availabilityRepository.findByLawyerId(lawyerId);
		// System.out.println("Total records fetched:" + availabilities.size());

		for (Availability a : availabilities) {
			System.out.println("ID: " + a.getId() + ", Date: " + a.getDate() + ", Lawyer: " + a.getLawyer().getName());
		}
		return availabilities.stream().map(a -> new AvailabilityResponseDTO(a.getId(), a.getDate(), a.getStartTime(),
				a.getEndTime(), a.getLawyer().getName())).collect(Collectors.toList());
	}

	@Override
	public AvailabilityResponseDTO addAvailability(@Valid AvailabilityRequestDTO dto) {

		Lawyer lawyer = lawyerRepository.findById(dto.getLawyerId())
				.orElseThrow(() -> new DataNotFound("lawyer not found"));

		Availability availability = new Availability();
		availability.setLawyer(lawyer);
		availability.setDate(dto.getDate());
		availability.setStartTime(dto.getStartTime());
		availability.setEndTime(dto.getEndTime());

		Availability saved = availabilityRepository.save(availability);

		return new AvailabilityResponseDTO(saved.getId(), saved.getDate(), saved.getStartTime(), saved.getEndTime(),
				saved.getLawyer().getName());
	}

	@Override
	public List<AvailabilityResponseDTO> getAllAvailabilities() {
		List<Availability> availabilities = availabilityRepository.findAll();
		if (availabilities.isEmpty()) {
			throw new DataNotFound("No availabilities found");
		}
		return availabilities.stream().map(a -> new AvailabilityResponseDTO(a.getId(), a.getDate(), a.getStartTime(),
				a.getEndTime(), a.getLawyer().getName())).collect(Collectors.toList());
	}
}
