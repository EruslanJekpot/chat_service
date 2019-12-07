package com.netcracker.project.service;

import com.netcracker.project.domain.Attendee;
import com.netcracker.project.domain.Chat;
import com.netcracker.project.repository.AttendeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
public class AttendeeService {
    private AttendeeRepository attendeeRepository;

    @Autowired
    public AttendeeService(AttendeeRepository attendeeRepository) {
        this.attendeeRepository = attendeeRepository;
    }

    public Attendee findByAttendeeId(UUID attendeeId) {
        return attendeeRepository.findByAttendeeId(attendeeId);
    }

    public Attendee findAttendeeByUserId(String userId) {
        return attendeeRepository.findAttendeeByUserId(userId);
    }

    public String getAttendeeSkills(UUID attendeeId) {
        return attendeeRepository.findByAttendeeId(attendeeId).getSkills();
    }

    public Attendee saveAttendee(Attendee attendee) {
        return attendeeRepository.save(attendee);
    }

    // идём по всем чатам нашего аттенди, и берём оттуда всех аттенди, которых ещё нет в хэшмапе
    @Transactional
    public HashMap<String, String> getAttendeeCompany(String userId) {
        HashMap<String, String> result = new HashMap<>();
        String attIdFromList;
        String fullName;
        Attendee currentAttendee = attendeeRepository.findAttendeeByUserId(userId);
        List<Chat> chats = currentAttendee.getChatList();
        for (Chat chat : chats) {
            List<Attendee> attendees = chat.getAttendeeList();
            for (Attendee attendee : attendees) {
                attIdFromList = attendee.getAttendeeId().toString();
                fullName = attendee.getSurname() + " " + attendee.getName();
                if ((!attIdFromList.equals(currentAttendee.getAttendeeId().toString())) & !result.containsKey(attIdFromList)) {
                    result.put(attIdFromList, fullName);
                }
            }
        }
        return result;
    }

    public byte[] extractBytes(String ImageName) throws IOException {
        File imgPath = new File(ImageName);
        BufferedImage bufferedImage = ImageIO.read(imgPath);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "jpg", bos);
        return (bos.toByteArray());
    }

    public List<Attendee> getChatAttendees(Chat chat) {
        return attendeeRepository.findAttendeeByChatListContaining(chat);
    }

    public Attendee getAttendeeByEmail(String email) {
        return attendeeRepository.findAttendeeByEmail(email);
    }
}