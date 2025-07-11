package com.gfmnow.appointmentscheduling.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import com.gfmnow.appointmentscheduling.audit.Auditable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Availability extends Auditable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private LocalDate date;

	private LocalTime startTime;

	private LocalTime endTime;

	@ManyToOne
	@JoinColumn(name = "lawyer_id", nullable = false)
	private Lawyer lawyer;

}
