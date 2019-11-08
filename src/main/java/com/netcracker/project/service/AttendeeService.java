package com.netcracker.project.service;

import com.netcracker.project.domain.Attendee;
import com.netcracker.project.repository.AttendeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendeeService {
    private AttendeeRepository attendeeRepository;

    @Autowired
    public AttendeeService(AttendeeRepository attendeeRepository) {
        this.attendeeRepository = attendeeRepository;
    }

    public Attendee findByAttendeeId(Long id){
        return this.attendeeRepository.findByAttendeeId(id);
    }

    //??
    public String getAttendeeSkills(Long id){
        return this.attendeeRepository.findByAttendeeId(id).getSkills();
    }

    public Boolean addAttendee(Attendee attendee) {
        try {
            attendeeRepository.save(attendee);
            return true;
        }catch (Exception e) {
            System.out.println("Ошибка при сохранении пользователя");
            return false;
        }
    }

    public Boolean updateAttendee(Attendee attendee) {
        try {
            attendeeRepository.updateAttendee(attendee.getAttendeeId(), attendee.getEmail(),
                    attendee.getName(), attendee.getSkills(), attendee.getSurname());
            return true;
        }catch (Exception e) {
            System.out.println("Ошибка при изменении данных пользователя");
            System.out.println(e);
            return false;
        }
    }
}