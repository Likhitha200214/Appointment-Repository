package com.gfmnow.appointmentscheduling.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gfmnow.appointmentscheduling.entity.Appointment;
import com.gfmnow.appointmentscheduling.entity.Lawyer;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

	boolean existsByLawyerAndAppointmentDateTime(Lawyer lawyer, LocalDateTime appointmentDateTime);

	List<Appointment> findByLawyerId(Integer lawyerId);

	List<Appointment> findByClientId(Integer clientId);

}
