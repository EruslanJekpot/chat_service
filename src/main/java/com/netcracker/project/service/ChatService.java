package com.netcracker.project.service;

import com.netcracker.project.domain.Attendee;
import com.netcracker.project.domain.Chat;
import com.netcracker.project.repository.AttendeeRepository;
import com.netcracker.project.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class ChatService {
    private ChatRepository chatRepository;
    private AttendeeRepository attendeeRepository;

    public ChatService(ChatRepository chatRepository, AttendeeRepository attendeeRepository) {
        this.chatRepository = chatRepository;
        this.attendeeRepository = attendeeRepository;
    }

    public Chat findByChatId(UUID id) {
        return chatRepository.findByChatId(id);
    }

    @Transactional
    public void saveChat(UUID creatorId, Chat chat) {
        Attendee attendee = attendeeRepository.findByAttendeeId(creatorId);
        attendee.getChatList().add(chat);
        attendeeRepository.save(attendee);
    }
}