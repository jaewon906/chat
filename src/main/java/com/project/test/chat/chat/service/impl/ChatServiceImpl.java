package com.project.test.chat.chat.service.impl;

import com.project.test.chat.chat.VO.ChatMessageVO;
import com.project.test.chat.chat.service.ChatService;
import com.project.test.chat.mapper.chat.ChatMapper;
import org.springframework.stereotype.Service;


@Service
public class ChatServiceImpl implements ChatService {

    private final ChatMapper chatMapper;

    ChatServiceImpl(ChatMapper chatMapper) {
        this.chatMapper = chatMapper;
    }

    public int createHistory(ChatMessageVO chatMessageVO){
        return chatMapper.createHistory(chatMessageVO);
    };
}
