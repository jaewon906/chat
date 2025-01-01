package com.project.test.chat.chat.VO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ChatMessageVO {
    private String sender;
    private String chatroomId;
    private String content;
    private String createDate;
}
