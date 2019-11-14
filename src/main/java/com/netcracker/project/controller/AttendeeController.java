package com.netcracker.project.controller;

import com.netcracker.project.domain.Attendee;
import com.netcracker.project.service.AttendeeService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping(path = "/save/attendee")
    public ResponseEntity saveAttendee(@RequestBody Attendee attendee) {
        attendeeService.saveAttendee(attendee);
        return ResponseEntity.ok().build();
    }

    @PatchMapping(path = "/update/attendee")
    public ResponseEntity updateAttendee(@RequestBody Attendee attendee) {
        attendeeService.saveAttendee(attendee);
        return ResponseEntity.ok().build();
    }
}