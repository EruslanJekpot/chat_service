package com.netcracker.project.service;

import com.netcracker.project.domain.Attendee;
import com.netcracker.project.repository.AttendeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AttendeeService {
    private AttendeeRepository attendeeRepository;

    @Autowired
    public AttendeeService(AttendeeRepository attendeeRepository) {
        this.attendeeRepository = attendeeRepository;
    }

    public Attendee findByAttendeeId(UUID id){
        return attendeeRepository.findByAttendeeId(id);
    }

    //??
    public String getAttendeeSkills(UUID id){
        return attendeeRepository.findByAttendeeId(id).getSkills();
    }

    public Attendee saveAttendee(Attendee attendee) {
        return attendeeRepository.save(attendee);
    }


}