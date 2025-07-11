package com.gfmnow.appointmentscheduling.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gfmnow.appointmentscheduling.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    com.gfmnow.appointmentscheduling.entity.User user = userRepository.findByUserName(username)
	            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

	    List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_"+user.getRole()));

	    return new org.springframework.security.core.userdetails.User(
	        user.getUserName(),
	        user.getPassword(),
	        authorities
	    );
	}

}
