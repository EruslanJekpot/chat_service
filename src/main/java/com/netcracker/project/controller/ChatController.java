package com.netcracker.project.controller;

import com.netcracker.project.Dto.ChatDto;
import com.netcracker.project.domain.Chat;
import com.netcracker.project.domain.Message;
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

    @PostMapping(path = "/attendee/{attendee_id}/chat")
    public ResponseEntity saveChat(@PathVariable("attendee_id") UUID attendeeId, @RequestBody Chat chat) {
        chatService.saveChat(attendeeId, chat);
        return ResponseEntity.ok().build();
    }

    // возвращает дто чата
    @GetMapping(path = "/chatDto/{chat_id}")
    public ResponseEntity<ChatDto> getChatDtoById(@PathVariable(value = "chat_id") UUID chatId){
        return ResponseEntity.ok().body(chatService.getChatDto(chatId));
    }

    // просто достаёт аттенди чата и его имя
    @GetMapping(path = "/chat/{chat_id}/members")
    public ResponseEntity<HashMap> getChatMembers(@PathVariable(value = "chat_id") UUID chatId) {
        return ResponseEntity.ok().body(chatService.getChatMembers(chatId));
    }
}