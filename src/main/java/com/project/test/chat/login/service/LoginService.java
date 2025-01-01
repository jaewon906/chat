package com.project.test.chat.login.service;

import com.project.test.chat.login.VO.LoginVO;

import java.util.List;

public interface LoginService {
    List<LoginVO> login(LoginVO loginVO);
}
