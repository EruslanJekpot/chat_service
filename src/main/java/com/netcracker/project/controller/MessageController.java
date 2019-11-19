package com.netcracker.project.controller;

import com.netcracker.project.domain.Attendee;
import com.netcracker.project.domain.Message;
import com.netcracker.project.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
public class MessageController {
    private MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping(path = "/save/message")
    public ResponseEntity saveMessage(@RequestBody Message message) {
        messageService.saveMessage(message);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/message/{chat_id}")
    public ResponseEntity<List<Message>> getTop3ByChatIdAndOrderByMessageDateDesc(@PathVariable(value = "chat_id") UUID chatId) {
        return ResponseEntity.ok().body(messageService.getTop3ByChatIdAndOrderByMessageDateDesc(chatId));
    }

    @GetMapping(path = "/attendees/{attendee_id}/chats")
    public ResponseEntity<HashMap> getChatsWithLastMessageByUserId(@PathVariable(value = "attendee_id") UUID attendeeId) {
        return ResponseEntity.ok().body(messageService.getChatsWithLastMessageByUserId(attendeeId));
    }
}