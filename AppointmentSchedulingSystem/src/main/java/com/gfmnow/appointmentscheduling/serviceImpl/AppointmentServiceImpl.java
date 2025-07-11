package com.gfmnow.appointmentscheduling.serviceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gfmnow.appointmentscheduling.entity.Appointment;
import com.gfmnow.appointmentscheduling.entity.AppointmentStatus;
import com.gfmnow.appointmentscheduling.entity.Availability;
import com.gfmnow.appointmentscheduling.entity.Client;
import com.gfmnow.appointmentscheduling.entity.Lawyer;
import com.gfmnow.appointmentscheduling.exception.BadRequestException;
import com.gfmnow.appointmentscheduling.exception.DataNotFound;
import com.gfmnow.appointmentscheduling.repository.AppointmentRepository;
import com.gfmnow.appointmentscheduling.repository.AvailabilityRepository;
import com.gfmnow.appointmentscheduling.repository.ClientRepository;
import com.gfmnow.appointmentscheduling.repository.LawyerRepository;
import com.gfmnow.appointmentscheduling.requestDTO.AppointmentRequestDTO;
import com.gfmnow.appointmentscheduling.requestDTO.RescheduleAppointmentDTO;
import com.gfmnow.appointmentscheduling.responseDTO.AppointmentResponseDTO;
import com.gfmnow.appointmentscheduling.service.AppointmentService;

import jakarta.validation.Valid;

@Service
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private LawyerRepository lawyerRepository;

	@Autowired
	private AvailabilityRepository availabilityRepository;

	@Override
	public Appointment scheduleAppointment(@Valid AppointmentRequestDTO dto) throws BadRequestException {
		Client client = clientRepository.findById(dto.getClientId())
				.orElseThrow(() -> new DataNotFound("Client Not found"));
		Lawyer lawyer = lawyerRepository.findById(dto.getLawyerId())
				.orElseThrow(() -> new DataNotFound("lawyer not found"));
		LocalDateTime dateTime = dto.getAppointmentDateTime();
		if (dateTime.isBefore(LocalDateTime.now())) {
			throw new BadRequestException("Appointment must be scheduled in the future");
		}
		List<Availability> availabilities = availabilityRepository.findByLawyerId(lawyer.getId());

		boolean available = availabilities.stream()
				.anyMatch(slot -> slot.getDate().equals(dateTime.toLocalDate())
						&& !dateTime.toLocalTime().isBefore(slot.getStartTime())
						&& !dateTime.toLocalTime().isAfter(slot.getEndTime()));
		if (!available) {
			throw new BadRequestException("lawyer is not available at this time");
		}
		boolean alreadyBooked = appointmentRepository.existsByLawyerAndAppointmentDateTime(lawyer, dateTime);
		if (alreadyBooked) {
			throw new BadRequestException("this slot is alreay boooked");
		}
		Appointment appointment = new Appointment();
		appointment.setClient(client);
		appointment.setLawyer(lawyer);
		appointment.setStatus(AppointmentStatus.SCHEDULED);
		appointment.setAppointmentDateTime(dateTime);

		return appointmentRepository.save(appointment);
	}

	@Override
	public Appointment rescheduleAppointment(@Valid RescheduleAppointmentDTO dto) {
		Appointment appointment = appointmentRepository.findById(dto.getAppointmentId())
				.orElseThrow(() -> new DataNotFound("Appointment not found"));
		LocalDateTime newDateTime = dto.getNewAppointmentDateTime();
		if (newDateTime.isBefore(LocalDateTime.now())) {
			throw new BadRequestException("Appointment must be rescheduled to a future time");
		}
		Lawyer lawyer = appointment.getLawyer();
		List<Availability> availabilities = availabilityRepository.findByLawyerId(lawyer.getId());
		boolean available = availabilities.stream()
				.anyMatch(slot -> slot.getDate().equals(newDateTime.toLocalDate())
						&& !newDateTime.toLocalTime().isBefore(slot.getStartTime())
						&& !newDateTime.toLocalTime().isAfter(slot.getEndTime()));

		if (!available) {
			throw new BadRequestException("Lawyer not available at this new time");
		}
		boolean alreadyBooked = appointmentRepository.existsByLawyerAndAppointmentDateTime(lawyer, newDateTime);
		if (alreadyBooked) {
			throw new BadRequestException("This slot is already booked");
		}
		appointment.setAppointmentDateTime(newDateTime);
		appointment.setStatus(AppointmentStatus.SCHEDULED);
		return appointmentRepository.save(appointment);
	}

	@Override
	public void cancelAppointment(Integer appointmentId) {
		Appointment appointment = appointmentRepository.findById(appointmentId)
				.orElseThrow(() -> new DataNotFound("Appointment not found with ID: " + appointmentId));
		if (appointment.getStatus() == AppointmentStatus.CANCELLED) {
			throw new BadRequestException("Appointment is already cancelled");
		}
		if (appointment.getAppointmentDateTime().isBefore(LocalDateTime.now())) {
			throw new BadRequestException("Cannot cancel a past appointment");
		}
		appointment.setStatus(AppointmentStatus.CANCELLED);
		appointmentRepository.save(appointment);
	}

	@Override
	public List<AppointmentResponseDTO> getAppointmentsByLawyerId(Integer lawyerId) {
		Lawyer lawyer = lawyerRepository.findById(lawyerId)
				.orElseThrow(() -> new DataNotFound("Lawyer not found with id: " + lawyerId));
		List<Appointment> appointments = appointmentRepository.findByLawyerId(lawyerId);
		if (appointments.isEmpty()) {
			throw new DataNotFound("No appointments found for the lawyer");
		}
		List<AppointmentResponseDTO> dtos = appointments.stream().map(app -> {
			Integer id = app.getId();
			String clientName = app.getClient() != null ? app.getClient().getName() : "NULL_CLIENT";
			String lawyerName = app.getLawyer() != null ? app.getLawyer().getName() : "NULL_LAWYER";
			LocalDateTime dateTime = app.getAppointmentDateTime();
			String status = app.getStatus() != null ? app.getStatus().name() : "NULL_STATUS";
			return new AppointmentResponseDTO(id, clientName, lawyerName, dateTime, status);
		}).collect(Collectors.toList());

		return dtos;
	}

	@Override
	public List<AppointmentResponseDTO> getAppointmentsByClientId(Integer clientId) {
		Client client = clientRepository.findById(clientId)
				.orElseThrow(() -> new DataNotFound("Client not found with id: " + clientId));
		List<Appointment> appointments = appointmentRepository.findByClientId(clientId);
		if (appointments.isEmpty()) {
			throw new DataNotFound("No appointments found for the client");
		}
		List<AppointmentResponseDTO> response = appointments.stream().map(app -> {
			Integer id = app.getId();
			String lawyerName = app.getLawyer() != null ? app.getLawyer().getName() : "NULL_LAWYER";
			String clientName = app.getClient() != null ? app.getClient().getName() : "NULL_CLIENT";
			LocalDateTime dateTime = app.getAppointmentDateTime();
			String status = app.getStatus() != null ? app.getStatus().name() : "NULL_STATUS";
			return new AppointmentResponseDTO(id, clientName, lawyerName, dateTime, status);
		}).collect(Collectors.toList());
		return response;
	}

}
