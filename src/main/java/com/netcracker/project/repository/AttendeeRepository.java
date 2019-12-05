package com.netcracker.project.repository;

import com.netcracker.project.domain.Attendee;
import com.netcracker.project.domain.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AttendeeRepository extends JpaRepository<Attendee, UUID> {
    Attendee findByAttendeeId(UUID id);

    Attendee findAttendeeByUserId(String userId);

    List<Attendee> findAttendeeByChatListContaining(Chat chat);
}

