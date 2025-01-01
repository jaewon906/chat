package com.project.test.chat.mapper.login;

import com.project.test.chat.chat.VO.ChatMessageVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface LoginMapper {
    List<Object> testSelect(ChatMessageVO chatMessageVO);
}
