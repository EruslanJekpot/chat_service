package com.netcracker.project.controller;

import com.netcracker.project.domain.Attendee;
import com.netcracker.project.domain.Message;
import com.netcracker.project.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
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

    @MessageMapping("/chat.addUser")
    @SendTo("/topic/chat")
    public ResponseEntity addUser(@Payload Message message, SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", message.getSender());
        return ResponseEntity.ok().body(message);
    }

    @GetMapping(path = "/message/{chat_id}")
    public ResponseEntity<List<Message>> getTop3ByChatIdAndOrderByMessageDateDesc(@PathVariable(value = "chat_id") UUID chatId) {
        return ResponseEntity.ok().body(messageService.getTop3ByChatIdAndOrderByMessageDateDesc(chatId));
    }

    @GetMapping(path = "/attendees/chats")
    public ResponseEntity<HashMap> getChatsWithLastMessageByUserId(@RequestHeader(value = "uid") String userId) {
        return ResponseEntity.ok().body(messageService.getChatsWithLastMessageByUserId(userId));
    }

    //poka net
    //nikogda ne budet
//    @MessageMapping("/changeMessage")
//    @SendTo("/topic/activity")
//    public Message change(Message message) {
//        return null;
//    }
}