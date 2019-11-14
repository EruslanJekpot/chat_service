package com.netcracker.project.service;

import com.netcracker.project.domain.Chat;
import com.netcracker.project.repository.AttendeeRepository;
import com.netcracker.project.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ChatService {
    private ChatRepository chatRepository;
    private AttendeeRepository attendeeRepository;

    @Autowired
    public ChatService(ChatRepository chatRepository){
        this.chatRepository = chatRepository;
    }

    public Chat findByChatId(UUID id) {
        return chatRepository.findByChatId(id);
    }

    public void saveChat(Chat chat) {
        chatRepository.save(chat);
    }
}