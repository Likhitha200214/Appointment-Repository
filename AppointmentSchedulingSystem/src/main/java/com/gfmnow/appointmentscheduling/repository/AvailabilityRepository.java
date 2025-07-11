package com.gfmnow.appointmentscheduling.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gfmnow.appointmentscheduling.entity.Availability;

public interface AvailabilityRepository extends JpaRepository<Availability, Integer> {

	List<Availability> findByLawyerId(Integer lawyerId);
}
