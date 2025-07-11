package com.gfmnow.appointmentscheduling.service;

import java.util.List;

import com.gfmnow.appointmentscheduling.entity.Appointment;
import com.gfmnow.appointmentscheduling.requestDTO.AppointmentRequestDTO;
import com.gfmnow.appointmentscheduling.requestDTO.RescheduleAppointmentDTO;
import com.gfmnow.appointmentscheduling.responseDTO.AppointmentResponseDTO;

import jakarta.validation.Valid;

public interface AppointmentService {

	Appointment scheduleAppointment(@Valid AppointmentRequestDTO dto);

	Appointment rescheduleAppointment(@Valid RescheduleAppointmentDTO dto);

	void cancelAppointment(Integer appointmentId);

	List<AppointmentResponseDTO> getAppointmentsByLawyerId(Integer lawyerId);

	List<AppointmentResponseDTO> getAppointmentsByClientId(Integer clientId);

}
