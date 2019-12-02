package com.netcracker.project.Dto;

import com.netcracker.project.domain.Attendee;
import com.netcracker.project.domain.Message;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
public class ChatDto {
    private String name;
    private List<Attendee> attendeeList;
    private List<Message> messageList;

    private String attendeeName;
    private String attendeeSurname;
}
