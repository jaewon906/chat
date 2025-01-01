package com.project.test.chat.mapper.signup;

import com.project.test.chat.signup.VO.SignupVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SignupMapper {
    int insertUserInfo(@Param("signupVO") SignupVO signupVO);
}
