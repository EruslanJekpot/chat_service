package com.netcracker.project.repository;

import com.netcracker.project.domain.Chat;
import com.netcracker.project.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MessageRepository extends JpaRepository<Message, UUID> {
    List<Message> findTop3ByChatIdOrderByMessageDateDesc(Chat chat);   // for 3 last message in chat

    Message findTop1ByChatIdOrderByMessageDateDesc(Chat chat);

    Message findByMessageId(UUID id);
}