package com.netcracker.project.controller;

import com.netcracker.project.domain.Attendee;
import com.netcracker.project.domain.Chat;
import com.netcracker.project.domain.Message;
import com.netcracker.project.service.ChatService;
import com.netcracker.project.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class WebsocketChatController {

    private final ChatService chatService;
    private final MessageService messageService;
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/create/chat")
    public void createChat(@Payload Chat chat) {
        chat = chatService.saveChat(chat);
        for (Attendee attendee: chat.getAttendeeList()) {
            messagingTemplate.convertAndSend("/queue/chats/"+attendee.getAttendeeId(), chat.getMessageList().get(0));
        }
    }

    @MessageMapping("/send/message")
    public void sendMessage(@Payload Message message) {
        message = messageService.saveMessage(message);
        messagingTemplate.convertAndSend("/topic/chat/"+message.getChatId().getChatId(), message);
    }
}
