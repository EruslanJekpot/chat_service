package com.netcracker.project.service;

import com.netcracker.project.domain.Message;
import com.netcracker.project.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    private MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Boolean addMessage(Message message) {
        try {
            messageRepository.save(message);
            return true;
        }catch (Exception e) {
            System.out.println("Ошибка при сохранении сообщения");
            return false;
        }
    }

    public List<Message> getLastMessages(Long chatId){
        return this.messageRepository.getLastMessages(chatId);
    }
}