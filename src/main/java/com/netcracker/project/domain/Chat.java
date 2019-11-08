package com.netcracker.project.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "chat")
@NoArgsConstructor
public class Chat {
    @Id
    @Column(name = "chat_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull
    private Long chatId;
    @Column(name = "name")
    @NonNull
    private String name;
    @ManyToMany(mappedBy = "chatList")
    @JsonIgnore
    private List<Attendee> attendeeList;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "chatId")
    @JsonIgnore
    private List<Message> messageList;
}