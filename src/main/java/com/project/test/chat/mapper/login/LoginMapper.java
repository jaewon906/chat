package com.project.test.chat.mapper.login;

import com.project.test.chat.login.VO.LoginVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LoginMapper {
    List<LoginVO> login(@Param("loginVO") LoginVO loginVO);
}
