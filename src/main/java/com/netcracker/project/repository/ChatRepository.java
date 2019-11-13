package com.netcracker.project.repository;

import com.netcracker.project.domain.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ChatRepository extends JpaRepository<Chat, UUID> {
    Chat findByChatId(UUID id);
    Chat findByAttendeeId(UUID id);
}
