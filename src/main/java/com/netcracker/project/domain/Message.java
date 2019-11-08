package com.netcracker.project.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity @Data @Table(name = "message") @NoArgsConstructor
public class Message {
    @Id
    @Column(name = "msg_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;
    @NonNull
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "chat_id")
    private Chat chatId;
    @Column(name = "sender")
    @NonNull
    private Integer sender;
    @Column(name = "content")
    @NonNull
    private String content;
    @Column(name = "msg_date")
    @NonNull
    private Date messageDate;
}
