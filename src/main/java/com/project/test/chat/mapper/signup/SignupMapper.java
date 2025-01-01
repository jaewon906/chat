package com.project.test.chat.mapper.signup;

import com.project.test.chat.chat.VO.ChatMessageVO;
import com.project.test.chat.signup.VO.SignupVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SignupMapper {
    int insertUserInfo(@Param("signupVO") SignupVO signupVO);
}
