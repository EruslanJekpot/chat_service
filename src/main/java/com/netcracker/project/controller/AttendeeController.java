package com.netcracker.project.controller;

import com.netcracker.project.domain.Attendee;
import com.netcracker.project.service.AttendeeService;
import io.swagger.models.Model;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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

    @GetMapping(path = "/attendee/{attendee_id}/info")
    public ResponseEntity getAttendeeInfo(@PathVariable(value = "attendee_id") UUID attendeeId) {
        return ResponseEntity.ok().body(attendeeService.getAttendeeSkills(attendeeId));
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
    public ResponseEntity saveAttendee(Attendee attendee) {
        byte[] image = null;
        try { image = attendeeService.extractBytes(".attendeeImage.jpeg");
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

    @GetMapping(path = "/attendee/{attendee_id}/company")
    public ResponseEntity<HashMap> getAttendeeCompany(@PathVariable(value = "attendee_id") UUID attendeeId) {
        return ResponseEntity.ok().body(attendeeService.getAttendeeCompany(attendeeId));
    }
}