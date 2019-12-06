package com.netcracker.project.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(url = "http://localhost:8092")
public interface AttendeeClient {

    @GetMapping(path = "/attendee/names")
    public List getParticipantsId(List usersIdList);
}
