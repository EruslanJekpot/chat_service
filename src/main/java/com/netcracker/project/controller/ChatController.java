package com.netcracker.project.controller;

import com.netcracker.project.domain.Chat;
import com.netcracker.project.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.UUID;

@RestController
@Component
public class ChatController {
    private ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @PostMapping(path = "/attendee/{attendee_id}/chat")
    public ResponseEntity saveChat(@PathVariable("attendee_id") UUID attendeeId, @RequestBody Chat chat) {
        chatService.saveChat(attendeeId, chat);
        return ResponseEntity.ok().build();
    }

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent sessionConnectedEvent) {
        System.out.println("Received a new web socket connection");
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent sessionDisconnectEvent) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(sessionDisconnectEvent.getMessage());
    }
}