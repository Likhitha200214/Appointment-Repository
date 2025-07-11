package com.gfmnow.appointmentscheduling.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
@Builder
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String userName;

	private String password;

	@Column(nullable = false)
	private String role;

	public Role getRoleEnum() {
		return Role.valueOf(role);
	}

	public void setRoleEnum(Role role) {
		this.role = role.name();
	}
}
