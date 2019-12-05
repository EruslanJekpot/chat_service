package com.netcracker.project.service;

import com.netcracker.project.domain.Attendee;
import com.netcracker.project.domain.Chat;
import com.netcracker.project.domain.Message;
import com.netcracker.project.repository.AttendeeRepository;
import com.netcracker.project.repository.ChatRepository;
import com.netcracker.project.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    @Transactional
    public Message saveMessage(Message message) {
        Attendee attendee = attendeeRepository.findByAttendeeId(UUID.fromString(message.getSender()));
        message.setSender(attendee.getSurname() + " " + attendee.getName());
        Chat chat = chatRepository.findByChatId(message.getChatId().getChatId());
        message.setChatId(chat);
        return messageRepository.save(message);
    }

    @Transactional
    public List<Message> getTop15ByChatIdAndOrderByMessageDateDesc(UUID chatId) {
        return messageRepository.findTop15ByChatIdOrderByMessageDateDesc(chatRepository.findByChatId(chatId));
    }

    @Transactional
    public ArrayList<Message> getChatsWithLastMessageByUserId(String userId) {
        List<Chat> chats = attendeeRepository.findAttendeeByUserId(userId).getChatList();
        ArrayList<Message> result = new ArrayList<>();
        Message message;
        for (Chat chat : chats) {
            message = messageRepository.findTop1ByChatIdOrderByMessageDateDesc(chat);
            if (message != null) {
                result.add(message);
            }
        }
        return result;
    }
}