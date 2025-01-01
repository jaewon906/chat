package com.project.test.chat.mapper.chat;

import com.project.test.chat.chat.VO.ChatMessageVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ChatMapper {
    int createHistory(@Param("chatMessageVO") ChatMessageVO chatMessageVO);
}
