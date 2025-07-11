package com.tp.gfmnow.appointmentschedulingsystem;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.gfmnow.appointmentscheduling.entity.Appointment;
import com.gfmnow.appointmentscheduling.entity.AppointmentStatus;
import com.gfmnow.appointmentscheduling.entity.Availability;
import com.gfmnow.appointmentscheduling.entity.Client;
import com.gfmnow.appointmentscheduling.entity.Lawyer;
import com.gfmnow.appointmentscheduling.exception.DataNotFound;
import com.gfmnow.appointmentscheduling.repository.AppointmentRepository;
import com.gfmnow.appointmentscheduling.repository.AvailabilityRepository;
import com.gfmnow.appointmentscheduling.repository.ClientRepository;
import com.gfmnow.appointmentscheduling.repository.LawyerRepository;
import com.gfmnow.appointmentscheduling.requestDTO.AppointmentRequestDTO;
import com.gfmnow.appointmentscheduling.requestDTO.RescheduleAppointmentDTO;
import com.gfmnow.appointmentscheduling.serviceImpl.AppointmentServiceImpl;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class AppointmentServiceImplTest {

    @InjectMocks
    private AppointmentServiceImpl appointmentService;

    @Mock
    private AppointmentRepository appointmentRepository;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private LawyerRepository lawyerRepository;

    @Mock
    private AvailabilityRepository availabilityRepository;

    private Client client;
    private Lawyer lawyer;
    private LocalDateTime futureDateTime;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        client = new Client();
        client.setId(1);
        client.setName("Test Client");

        lawyer = new Lawyer();
        lawyer.setId(1);
        lawyer.setName("Test Lawyer");

        futureDateTime = LocalDateTime.now().plusDays(1);
    }

    @Test
    void testScheduleAppointment_Success() {
        AppointmentRequestDTO dto = new AppointmentRequestDTO();
        dto.setClientId(1);
        dto.setLawyerId(2);
        dto.setAppointmentDateTime(LocalDateTime.now().plusDays(1));

        Client client = new Client(); client.setId(1); client.setName("Likhitha");
        Lawyer lawyer = new Lawyer(); lawyer.setId(2); lawyer.setName("ram");

        Availability availability = new Availability();
        availability.setDate(dto.getAppointmentDateTime().toLocalDate());
        availability.setStartTime(LocalTime.of(9, 0));
        availability.setEndTime(LocalTime.of(18, 0));

        Mockito.when(clientRepository.findById(1)).thenReturn(Optional.of(client));
        Mockito.when(lawyerRepository.findById(2)).thenReturn(Optional.of(lawyer));
        Mockito.when(availabilityRepository.findByLawyerId(2)).thenReturn(List.of(availability));
        Mockito.when(appointmentRepository.existsByLawyerAndAppointmentDateTime(Mockito.any(), Mockito.any())).thenReturn(false);
        Mockito.when(appointmentRepository.save(Mockito.any())).thenAnswer(i -> i.getArguments()[0]);

        Appointment result = appointmentService.scheduleAppointment(dto);

        assertNotNull(result);
        assertEquals(client, result.getClient());
        assertEquals(lawyer, result.getLawyer());
        assertEquals(AppointmentStatus.SCHEDULED, result.getStatus());
    }

    @Test
    void testScheduleAppointment_LawyerNotFound_ThrowsException() {
        AppointmentRequestDTO dto = new AppointmentRequestDTO();
        dto.setLawyerId(999);
        dto.setClientId(1);
        dto.setAppointmentDateTime(LocalDateTime.now().plusDays(1));

        Mockito.when(clientRepository.findById(1)).thenReturn(Optional.of(new Client()));
        Mockito.when(lawyerRepository.findById(999)).thenReturn(Optional.empty());

        assertThrows(DataNotFound.class, () -> appointmentService.scheduleAppointment(dto));
    }
    
    @Test
    void testRescheduleAppointment_Success() {
    	RescheduleAppointmentDTO dto=new RescheduleAppointmentDTO();
    	dto.setAppointmentId(1);
    	LocalDateTime newDateTime=LocalDateTime.now().plusDays(2).withHour(10).withMinute(0);
    	
    	Lawyer lawyer=new Lawyer();
    	lawyer.setId(1);
    	
    	Appointment appointment=new Appointment();
    	appointment.setId(1);
    	appointment.setLawyer(lawyer);
    	appointment.setAppointmentDateTime(LocalDateTime.now().plusDays(1));
    	
    	Availability availability= new Availability();
    	availability.setDate(newDateTime.toLocalDate());
    	availability.setStartTime(LocalTime.of(9, 0));
    	availability.setEndTime(LocalTime.of(11, 0));
    	
    	Mockito.when(appointmentRepository.findById(1)).thenReturn(Optional.of(appointment));
    	Mockito.when(availabilityRepository.findByLawyerId(1)).thenReturn(List.of(availability));
    	
    }
    

}
