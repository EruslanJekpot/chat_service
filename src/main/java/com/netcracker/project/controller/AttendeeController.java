package com.netcracker.project.controller;

import com.netcracker.project.domain.Attendee;
import com.netcracker.project.service.AttendeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
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

    @GetMapping(path = "/attendee/{attendee_id}/")
    public ResponseEntity getAttendeeInfo(@PathVariable(value = "attendee_id") UUID attendeeId) {
        return ResponseEntity.ok().body(attendeeService.getAttendeeSkills(attendeeId));
    }

    public ResponseEntity getAttendeeProfile(@RequestHeader("uid") String userId) {
        Attendee attendee = attendeeService.getAttendeeByUserId(userId);
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
        Attendee thisAttendee = attendeeService.getAttendeeByUserId(userId);
        thisAttendee.setImage(attendee.getImage());
        thisAttendee.setName(attendee.getName());
        thisAttendee.setSkills(attendee.getSkills());
        thisAttendee.setSurname(attendee.getSurname());
        attendeeService.saveAttendee(attendee);
        return ResponseEntity.ok().build();
    }
}