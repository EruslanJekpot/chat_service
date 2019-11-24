package com.netcracker.project.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Blob;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "attendee")
@NoArgsConstructor
public class Attendee {
    @Id
    @Column(name = "attendee_id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy ="org.hibernate.id.UUIDGenerator")
    private UUID attendeeId;
    @Column(name = "user_id", unique = true)
    @NonNull
    @JsonIgnore
    private String userId;
    @Column(name = "email", unique = true)
    @NonNull
    private String email;
    @Column(name = "name")
    @NonNull
    private String name;
    @Column(name = "surname")
    @NonNull
    private String surname;
    @Column(name = "skills")
    private String skills;
    @Column(name = "image")
    private byte[] image;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JsonIgnore
    @JoinTable(name = "chat_members",
            joinColumns = @JoinColumn(name = "attendee_id"),
    inverseJoinColumns = @JoinColumn(name = "chat_id"))
    private List<Chat> chatList;
}