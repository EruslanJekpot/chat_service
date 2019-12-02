package com.netcracker.project.Dto;

import com.netcracker.project.domain.Chat;
import lombok.NonNull;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

public class MessageDto {

    private Chat chatId;

    private String attendeeName;

    private String content;

    private Date messageDate;

}
