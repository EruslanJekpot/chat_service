package com.netcracker.project.service;

import com.netcracker.project.domain.Attendee;
import com.netcracker.project.domain.Chat;
import com.netcracker.project.domain.Message;
import com.netcracker.project.repository.AttendeeRepository;
import com.netcracker.project.repository.ChatRepository;
import com.netcracker.project.repository.MessageRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Transactional
@Service
public class ChatService {
    private ChatRepository chatRepository;
    private AttendeeRepository attendeeRepository;
    private final MessageRepository messageRepository;
    private final ModelMapper modelMapper;

    public ChatService(ChatRepository chatRepository, AttendeeRepository attendeeRepository, MessageRepository messageRepository, ModelMapper modelMapper) {
        this.chatRepository = chatRepository;
        this.attendeeRepository = attendeeRepository;
        this.messageRepository = messageRepository;
        this.modelMapper = modelMapper;
    }

    public Chat saveChat(Chat chat) {
        ArrayList<UUID> chatMembersId = (ArrayList<UUID>) chat.getAttendeeList().stream()
                .map(Attendee::getAttendeeId).collect(Collectors.toList());
        ArrayList<Attendee> chatMembers = (ArrayList<Attendee>) chatMembersId.stream().
                map(id -> {
                    Attendee attendee = attendeeRepository.findByAttendeeId(id);
                    attendee.getChatList().add(chat);
                    return attendee;
                })
                .collect(Collectors.toList());
        chat.setAttendeeList(chatMembers);
        chatRepository.save(chat);
        Message message = chat.getMessageList().get(0);
        Attendee sender = attendeeRepository.findByAttendeeId(UUID.fromString(message.getSender()));
        message.setSender(sender.getSurname() + " " + sender.getName());
        message.setChatId(chat);
        messageRepository.save(message);
        return chat;
    }

    public HashMap<Attendee, String> getChatMembers(UUID chatId) {
        HashMap<Attendee, String> result = new HashMap<>();
        List<Attendee> attendees = chatRepository.findByChatId(chatId).getAttendeeList();
        for (Attendee attendee : attendees) {
            result.put(attendee, attendee.getName());
        }
        return result;
    }

    public Chat getChat(UUID chatId) {
        return chatRepository.findByChatId(chatId);
    }

    public Chat findByAttendees(String senderId, UUID receiverId) {
        Attendee sender = attendeeRepository.findAttendeeByUserId(senderId);
        Attendee receiver = attendeeRepository.findByAttendeeId(receiverId);
        List<Chat> chats = chatRepository.findChatByAttendeeListContains(sender);
        chats = chats.stream().filter((chat) ->
                chat.getAttendeeList().size() == 2 && chat.getAttendeeList().contains(receiver)
        ).collect(Collectors.toList());
        return (chats.size() != 0) ? chats.get(0) : null;
    }
}