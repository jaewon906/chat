package com.project.test.chat.login.service;

import com.project.test.chat.exception.LoginFailedException;
import com.project.test.chat.login.VO.LoginVO;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface LoginService {
    void login(HttpServletRequest req, LoginVO loginVO) throws LoginFailedException;
}
