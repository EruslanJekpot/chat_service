package com.netcracker.project.service;

import com.netcracker.project.domain.Attendee;
import com.netcracker.project.domain.Chat;
import com.netcracker.project.domain.Message;
import com.netcracker.project.repository.AttendeeRepository;
import com.netcracker.project.repository.ChatRepository;
import com.netcracker.project.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
public class MessageService {
    private MessageRepository messageRepository;
    private ChatRepository chatRepository;
    private AttendeeRepository attendeeRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository, ChatRepository chatRepository, AttendeeRepository attendeeRepository) {
        this.messageRepository = messageRepository;
        this.chatRepository = chatRepository;
        this.attendeeRepository = attendeeRepository;
    }

    // Метод для сохранения сообщения (нужен айди чата и айди юзера из хедера)
    public void saveMessage(String userId, UUID chatId) {
        Attendee attendee = attendeeRepository.findAttendeeByUserId(userId);
        Chat chat = chatRepository.findByChatId(chatId);
        Message message = new Message();
        message.setContent(message.getContent());
        message.setSender(attendee.getName());
        message.setMessageDate(message.getMessageDate());
        message.setChatId(chat);
        messageRepository.save(message);
    }

    @Transactional
    public List<Message> getTop3ByChatIdAndOrderByMessageDateDesc(UUID chatId) {
        return messageRepository.findTop3ByChatIdOrderByMessageDateDesc(chatRepository.findByChatId(chatId));
    }

    @Transactional
    public HashMap<Chat, Message> getChatsWithLastMessageByUserId(UUID attendeeId) {
        HashMap<Chat, Message> result = new HashMap<>();
        System.out.println("lel proverka");
        List<Chat> chats = attendeeRepository.findByAttendeeId(attendeeId).getChatList();
        for (Chat chat : chats) {
            result.put(chat, messageRepository.findTop1ByChatIdOrderByMessageDateDesc(chat));
        }
        return result;
    }
}