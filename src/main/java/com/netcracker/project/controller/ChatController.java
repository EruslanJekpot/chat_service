package com.netcracker.project.controller;

import com.netcracker.project.domain.Chat;
import com.netcracker.project.service.ChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ChatController {
    private ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping(path = "/save/chat")
    public ResponseEntity saveChat(@RequestBody Chat chat) {
        chatService.saveChat(chat);
        return ResponseEntity.ok().build();
    }


}