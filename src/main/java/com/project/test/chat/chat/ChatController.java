package com.project.test.chat.chat;

import com.project.test.chat.chat.VO.ChatMessageVO;
import com.project.test.chat.chat.service.impl.ChatServiceImpl;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    private final ChatServiceImpl chatService;

    ChatController(ChatServiceImpl chatService){
        this.chatService = chatService;
    }

    @MessageMapping("/chat") // 클라이언트가 보낼 경로
    @SendTo("/subscribe/getMessage") // 메시지가 브로드캐스팅될 경로
    public ChatMessageVO sendMessage(ChatMessageVO message) {
        message.setCreateDate(splitDate(message.getCreateDate()));
        chatService.createHistory(message);
        return message; // 메시지를 그대로 브로드캐스트
    }

    String splitDate(String timestamp){
        String[] split1 = timestamp.split("T");
        String[] split2 = split1[1].split("\\.");
        return split1[0] + " " + split2[0];
    }
}

