package com.netcracker.project.service;

import com.netcracker.project.Dto.ChatDto;
import com.netcracker.project.domain.Attendee;
import com.netcracker.project.domain.Chat;
import com.netcracker.project.domain.Message;
import com.netcracker.project.repository.AttendeeRepository;
import com.netcracker.project.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
public class ChatService {
    private ChatRepository chatRepository;
    private AttendeeRepository attendeeRepository;

    public ChatService(ChatRepository chatRepository, AttendeeRepository attendeeRepository) {
        this.chatRepository = chatRepository;
        this.attendeeRepository = attendeeRepository;
    }

    @Transactional
    public void saveChat(UUID creatorId, Chat chat) {
        Attendee attendee = attendeeRepository.findByAttendeeId(creatorId);
        attendee.getChatList().add(chat);
        attendeeRepository.save(attendee);
    }

    @Autowired
    private ModelMapper modelMapper;

    public HashMap<Attendee, String> getChatMembers(UUID chatId) {
        HashMap<Attendee, String> result = new HashMap<>();
        List<Attendee> attendees = chatRepository.findByChatId(chatId).getAttendeeList();
        for (Attendee attendee : attendees) {
            result.put(attendee, attendee.getName());
        }
        return result;
    }

    public ChatDto getChatDto(UUID chatId) {
        Chat chat = chatRepository.findByChatId(chatId);
        ChatDto chatDto = modelMapper.map(chat, ChatDto.class);
        return chatDto;
    }
}