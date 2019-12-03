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

    public Message saveMessage(Message message) {
        Attendee attendee = attendeeRepository.findByAttendeeId(UUID.fromString(message.getSender()));
        message.setSender(attendee.getSurname()+" "+attendee.getName());
        Chat chat = chatRepository.findByChatId(message.getChatId().getChatId());
        message.setChatId(chat);
        return messageRepository.save(message);
    }

    @Transactional
    public List<Message> getTop3ByChatIdAndOrderByMessageDateDesc(UUID chatId) {
        return messageRepository.findTop3ByChatIdOrderByMessageDateDesc(chatRepository.findByChatId(chatId));
    }

    @Transactional
    public HashMap<Chat, Message> getChatsWithLastMessageByUserId(String userId) {
        HashMap<Chat, Message> result = new HashMap<>();
        System.out.println("lel proverka");
        List<Chat> chats = attendeeRepository.findAttendeeByUserId(userId).getChatList();
        for (Chat chat : chats) {
            result.put(chat, messageRepository.findTop1ByChatIdOrderByMessageDateDesc(chat));
        }
        return result;
    }
}