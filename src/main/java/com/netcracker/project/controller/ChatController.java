package com.netcracker.project.controller;

import com.netcracker.project.domain.Chat;
import com.netcracker.project.service.ChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ChatController {
    private ChatService chatService;

    public ChatController(ChatService chatService) {
        chatService = chatService;
    }

    @PostMapping(path = "/save/chat")
    public ResponseEntity saveChat(@RequestBody Chat chat){
        return ResponseEntity.ok().body(chatService.saveChat(chat));
    }
}