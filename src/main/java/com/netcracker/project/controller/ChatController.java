package com.netcracker.project.controller;

import com.netcracker.project.domain.Chat;
import com.netcracker.project.service.ChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.UUID;

@RestController
@Component
@Slf4j
public class ChatController {
    private ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping(path = "/chat/{chat_id}")
    public ResponseEntity getChatById (@PathVariable(value = "chat_id") UUID chatId){
        return ResponseEntity.ok().body(chatService.getChat(chatId));
    }

    // просто достаёт аттенди чата и их имена
    @GetMapping(path = "/chat/{chat_id}/members")
    public ResponseEntity<HashMap> getChatMembers(@PathVariable(value = "chat_id") UUID chatId) {
        return ResponseEntity.ok().body(chatService.getChatMembers(chatId));
    }
}