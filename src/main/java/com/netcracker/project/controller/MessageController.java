package com.netcracker.project.controller;

import com.netcracker.project.domain.Message;
import com.netcracker.project.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class MessageController {
    private MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/chat")
    public ResponseEntity addUser(@Payload Message message, SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", message.getSender());
        return ResponseEntity.ok().body(message);
    }

    @GetMapping(path = "/message/{chat_id}")
    public ResponseEntity<List<Message>> getTop15ByChatIdAndOrderByMessageDateDesc(@PathVariable(value = "chat_id") UUID chatId) {
        return ResponseEntity.ok().body(messageService.getTop15ByChatIdAndOrderByMessageDateDesc(chatId));
    }

    @GetMapping(path = "/attendees/chats")
    public ResponseEntity<ArrayList> getChatsWithLastMessageByUserId(@RequestHeader(value = "uid") String userId) {
        return ResponseEntity.ok().body(messageService.getChatsWithLastMessageByUserId(userId));
    }
}