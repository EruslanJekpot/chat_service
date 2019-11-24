package com.netcracker.project.service;

import com.netcracker.project.domain.Attendee;
import com.netcracker.project.repository.AttendeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AttendeeService {
    private AttendeeRepository attendeeRepository;

    @Autowired
    public AttendeeService(AttendeeRepository attendeeRepository) {
        this.attendeeRepository = attendeeRepository;
    }

    public Attendee findByAttendeeId(UUID attendeeId){
        return attendeeRepository.findByAttendeeId(attendeeId);
    }

    public String getAttendeeSkills(UUID attendeeId){
        return attendeeRepository.findByAttendeeId(attendeeId).getSkills();
    }

    public Attendee saveAttendee(Attendee attendee) {
        return attendeeRepository.save(attendee);
    }

    public Attendee findAttendeeByUser(String id) {
        return attendeeRepository.findAttendeeByUserId(id);
    }
}