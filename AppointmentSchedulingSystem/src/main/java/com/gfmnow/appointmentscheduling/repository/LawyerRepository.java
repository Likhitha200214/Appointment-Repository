package com.gfmnow.appointmentscheduling.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gfmnow.appointmentscheduling.entity.Lawyer;


public interface LawyerRepository extends JpaRepository<Lawyer, Integer> {
	

}
