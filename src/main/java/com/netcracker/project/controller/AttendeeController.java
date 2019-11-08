package com.netcracker.project.controller;

import com.netcracker.project.domain.Attendee;
import com.netcracker.project.service.AttendeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AttendeeController {
    private AttendeeService attendeeService;

    @Autowired
    public AttendeeController(AttendeeService attendeeService) {
        this.attendeeService = attendeeService;
    }

    @GetMapping(path = "/attendee/{attendee_id}/info")
    public String getAttendeeInfo(@PathVariable(value = "attendee_id") Long attendeeId){
        return attendeeService.getAttendeeSkills(attendeeId);
    }

    @PostMapping(path = "/add/attendee")
    public void addAttendee(@RequestBody Attendee attendee){
        attendeeService.addAttendee(attendee);
    }

    @PatchMapping(path = "/attendee/update")
    public void updateAttendee(@RequestBody Attendee attendee){
        attendeeService.updateAttendee(attendee);
    }
}