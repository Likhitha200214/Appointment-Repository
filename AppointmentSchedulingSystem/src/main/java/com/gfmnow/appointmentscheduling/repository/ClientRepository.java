package com.gfmnow.appointmentscheduling.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gfmnow.appointmentscheduling.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {

}
