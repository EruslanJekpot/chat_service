package com.netcracker.project.controller;

import com.netcracker.project.domain.Message;
import com.netcracker.project.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {
    private MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping(path = "/add/message")
    public void addAttendee(@RequestBody Message message){
        messageService.addMessage(message);
    }

    @Transactional
    @GetMapping(path = "/message/{chat_id}")
    public List<Message> getLastMessages(@PathVariable(value = "chat_id") Long chatId){
        return messageService.getLastMessages(chatId);
    }
}