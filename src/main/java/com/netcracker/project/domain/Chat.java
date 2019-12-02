package com.netcracker.project.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.GenericGenerator;

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
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy ="org.hibernate.id.UUIDGenerator")
    private UUID chatId;
    @Column(name = "name")
    @NonNull
    private String name;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "chatList", cascade = CascadeType.MERGE)
    @JsonIgnore
    private List<Attendee> attendeeList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "chatId")
    @JsonIgnore
    private List<Message> messageList;
}