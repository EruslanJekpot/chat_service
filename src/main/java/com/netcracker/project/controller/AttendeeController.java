package com.netcracker.project.controller;

import com.netcracker.project.domain.Attendee;
import com.netcracker.project.service.AttendeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Component
@Slf4j
@RestController
public class AttendeeController {
    private AttendeeService attendeeService;

    @Autowired
    public AttendeeController(AttendeeService attendeeService) {
        this.attendeeService = attendeeService;
    }

    // получаем list стринговых айди
    @GetMapping(path = "/event/participantsList")
    public ResponseEntity getParticipantsId(List usersIdList) {
        return ResponseEntity.ok().body(attendeeService.getAttendeesName(usersIdList));
    }

    // толкаем хэшмап со стринговыми айди и именами
    @PostMapping(path = "attendee/names")
    public ResponseEntity postAttendeesName(List attendeesName) {
        return ResponseEntity.ok().body(attendeeService.getAttendeesName(attendeesName));
    }

    @GetMapping(path = "/attendee/{attendee_id}/info")
    public ResponseEntity getAttendeeSkills(@PathVariable(value = "attendee_id") UUID attendeeId) {
        return ResponseEntity.ok().body(attendeeService.getAttendeeSkills(attendeeId));
    }

    @GetMapping(path = "/attendee/{attendee_id}")
    public ResponseEntity getAttendee(@PathVariable(value = "attendee_id") UUID attendeeId) {
        return ResponseEntity.ok().body(attendeeService.findByAttendeeId(attendeeId));
    }

    @GetMapping(path = "/attendee/id")
    public ResponseEntity getAttendeeId(@RequestHeader(name = "uid") String uid) {
        return ResponseEntity.ok().body(attendeeService.findAttendeeByUserId(uid).getAttendeeId());
    }

    @GetMapping(path = "/attendee/profile")
    public ResponseEntity getAttendeeProfile(@RequestHeader("uid") String userId) {
        Attendee attendee = attendeeService.findAttendeeByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(attendee);
    }

    @PostMapping(path = "/save/attendee")
    public ResponseEntity saveAttendee(@RequestBody Attendee attendee) {
        byte[] image;
        Attendee existingAttendee = attendeeService.getAttendeeByEmail(attendee.getEmail());
        if (existingAttendee != null) {
            return ResponseEntity.badRequest().body("email in use");
        }
        try {
            image = attendeeService.extractBytes("src/main/resources/static/attendeeImage.jpg");
        } catch (Exception exc) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error loading image");
        }
        attendee.setImage(image);
        attendeeService.saveAttendee(attendee);
        return ResponseEntity.ok().build();
    }

    @PatchMapping(path = "/update/attendee")
    public ResponseEntity updateAttendee(@RequestHeader("uid") String userId, @RequestBody Attendee attendee) {
        Attendee att = attendeeService.findAttendeeByUserId(userId);
        att.setImage(attendee.getImage());
        att.setEmail(attendee.getEmail());
        att.setSkills(attendee.getSkills());
        attendeeService.saveAttendee(att);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/attendee/company")
    public ResponseEntity<HashMap> getAttendeeCompany(@RequestHeader(value = "uid") String uid) {
        return ResponseEntity.ok().body(attendeeService.getAttendeeCompany(uid));
    }
}