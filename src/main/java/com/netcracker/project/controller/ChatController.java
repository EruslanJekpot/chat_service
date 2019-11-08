package com.netcracker.project.controller;

import com.netcracker.project.domain.Chat;
import com.netcracker.project.service.ChatService;
import org.springframework.web.bind.annotation.*;

@RestController
public class ChatController {
    private ChatService chatService;

    public ChatController(ChatService chatService){
        this.chatService = chatService;
    }

    @PostMapping(path = "/add/chat")
    public void addChat(@RequestBody Chat chat){
        chatService.addChat(chat);
    }
}