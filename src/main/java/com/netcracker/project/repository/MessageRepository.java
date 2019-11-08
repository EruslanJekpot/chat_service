package com.netcracker.project.repository;

import com.netcracker.project.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message,Long> {
    @Modifying
    @Transactional
    @Query(value="select content from message where chat_id=:chat_id order by msg_date desc limit 5", nativeQuery = true)
    public List<Message> getLastMessages(@Param("chat_id") Long id);
}