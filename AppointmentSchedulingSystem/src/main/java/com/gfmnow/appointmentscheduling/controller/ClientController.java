package com.gfmnow.appointmentscheduling.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gfmnow.appointmentscheduling.requestDTO.ClientRequestDTO;
import com.gfmnow.appointmentscheduling.responseDTO.ClientResponseDTO;
import com.gfmnow.appointmentscheduling.service.ClientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/register")
    public ResponseEntity<ClientResponseDTO> registerClient(@Valid @RequestBody ClientRequestDTO dto) {
        return new ResponseEntity<>(clientService.registerClient(dto), HttpStatus.CREATED);
    }
}
