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

    @PostMapping(path = "/save/attendee")
    public ResponseEntity saveAttendee(Attendee attendee)
    //                                   @RequestParam("file") MultipartFile file
    {
  //      attendee.setImage();
        attendeeService.saveAttendee(attendee);
        return ResponseEntity.ok().build();
    }

    @PatchMapping(path = "/update/attendee")
    public ResponseEntity updateAttendee(@RequestBody Attendee attendee,
                                         @RequestParam("image") MultipartFile image) {
        if (!image.isEmpty()) {
            try {
                attendee.setImage(image.getBytes());            //problema pri sohranenii
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
        attendeeService.saveAttendee(attendee);
        return ResponseEntity.ok().build();
    }
}