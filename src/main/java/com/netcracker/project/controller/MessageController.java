package com.netcracker.project.controller;

import com.netcracker.project.domain.Message;
import com.netcracker.project.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class MessageController {
    private MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        messageService = messageService;
    }

    @PostMapping(path = "/save/message")
    public ResponseEntity saveAttendee(@RequestBody Message message) {
        messageService.saveMessage(message);
        return ResponseEntity.ok().build();
    }

    @Transactional
    @GetMapping(path = "/message/{chat_id}")
    public ResponseEntity<List<Message>> getLastMessages(@PathVariable(value = "chat_id") UUID chatId) {
        return ResponseEntity.ok().body(messageService.getLastMessages(chatId));
    }
}