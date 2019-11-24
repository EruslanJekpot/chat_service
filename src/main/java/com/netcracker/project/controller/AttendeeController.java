package com.netcracker.project.controller;

import com.netcracker.project.domain.Attendee;
import com.netcracker.project.service.AttendeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class AttendeeController {
    private AttendeeService attendeeService;

    @Autowired
    public AttendeeController(AttendeeService attendeeService) {
        this.attendeeService = attendeeService;
    }

    @GetMapping(path = "/attendee/{attendee_id}/info")
    public ResponseEntity getAttendeeInfo(@PathVariable(value = "attendee_id") UUID attendeeId) {
        return ResponseEntity.ok().body(attendeeService.getAttendeeSkills(attendeeId));
    }

    @GetMapping(path = "/attendee/{attendee_id}")
    public ResponseEntity getAttendee(@PathVariable(value = "attendee_id") UUID attendeeId) {
        return ResponseEntity.ok().body(attendeeService.findByAttendeeId(attendeeId));
    }

    @GetMapping(path = "/attendee/profile")
    public ResponseEntity getProfile(@RequestHeader("uid") String uid) {
        Attendee attendee = attendeeService.findAttendeeByUser(uid);
        return ResponseEntity.status(HttpStatus.OK).body(attendee);
    }

    @PostMapping(path = "/save/attendee")
    public ResponseEntity saveAttendee(Attendee attendee) {
        attendeeService.saveAttendee(attendee);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/update/attendee")
    public ResponseEntity updateAttendee(@RequestHeader("uid") String uid, @RequestBody Attendee attendee) {
        Attendee att = attendeeService.findAttendeeByUser(uid);
        att.setEmail(attendee.getEmail());
        att.setSkills(attendee.getSkills());
        att.setImage(attendee.getImage());
        attendeeService.saveAttendee(att);
        return ResponseEntity.ok().build();
    }
}