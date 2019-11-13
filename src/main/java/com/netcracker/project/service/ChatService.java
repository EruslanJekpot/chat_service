package com.netcracker.project.service;

import com.netcracker.project.domain.Chat;
import com.netcracker.project.domain.Message;
import com.netcracker.project.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
public class ChatService {
    private ChatRepository chatRepository;

    @Autowired
    public ChatService(ChatRepository chatRepository){
        this.chatRepository = chatRepository;
    }

    public Chat findByChatId(UUID id) {
        return chatRepository.findByChatId(id);
    }

    public List<Message> getChatMessages(UUID id) {
        return chatRepository.findByChatId(id).getMessageList();
    }

    public Chat saveChat(Chat chat) {
        return chatRepository.save(chat);
    }
}