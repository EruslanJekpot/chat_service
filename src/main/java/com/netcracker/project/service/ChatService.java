package com.netcracker.project.service;

import com.netcracker.project.domain.Chat;
import com.netcracker.project.domain.Message;
import com.netcracker.project.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {
    private ChatRepository chatRepository;

    @Autowired
    public ChatService(ChatRepository chatRepository){
        this.chatRepository = chatRepository;
    }

    public Chat findByChatId(Long id){
        return this.chatRepository.findByChatId(id);
    }

    public List<Message> getChatMessages(Long id){
        return this.chatRepository.findByChatId(id).getMessageList();
    }

    public Boolean addChat(Chat chat) {
        try {
            chatRepository.save(chat);
            return true;
        }catch (Exception e) {
            System.out.println("Ошибка при сохранении чата");
            return false;
        }
    }
}