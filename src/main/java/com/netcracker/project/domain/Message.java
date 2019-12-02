package com.netcracker.project.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
@Table(name = "message")
@NoArgsConstructor
public class Message {
    @Id
    @Column(name = "msg_id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy ="org.hibernate.id.UUIDGenerator")
    private UUID messageId;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_id")
    private Chat chatId;

    @NonNull
    @Column(name = "sender")
    private String sender;
    @Column(name = "content")
    @NonNull
    private String content;
    @Column(name = "msg_date")
    @NonNull
    private Date messageDate;
}
