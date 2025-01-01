package com.project.test.chat.chat.service;

import com.project.test.chat.chat.VO.ChatMessageVO;

public interface ChatService {
    int createHistory(ChatMessageVO chatMessageVO);
}
