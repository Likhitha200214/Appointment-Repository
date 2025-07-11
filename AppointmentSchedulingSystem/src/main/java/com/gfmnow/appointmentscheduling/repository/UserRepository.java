package com.gfmnow.appointmentscheduling.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gfmnow.appointmentscheduling.entity.User;


public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByUserName(String userName);

	void save(org.springframework.security.core.userdetails.User user);

}
