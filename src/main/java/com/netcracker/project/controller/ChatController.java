package com.netcracker.project.controller;

import com.netcracker.project.domain.Chat;
import com.netcracker.project.service.ChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class ChatController {
    private ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping(path = "/attendee/{attendee_id}/chat")
    public ResponseEntity saveChat(@PathVariable("attendee_id") UUID attendeeId, @RequestBody Chat chat) {
        chatService.saveChat(attendeeId, chat);
        return ResponseEntity.ok().build();
    }


}