package com.gfmnow.appointmentscheduling.entity;

import java.time.LocalDateTime;

import com.gfmnow.appointmentscheduling.audit.Auditable;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Appointment extends Auditable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	private Client client;

	@ManyToOne
	private Lawyer lawyer;

	private LocalDateTime appointmentDateTime;

	@Enumerated(EnumType.STRING)
	private AppointmentStatus status;

}
