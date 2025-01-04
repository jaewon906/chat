package com.project.test.chat.login.service;

import com.project.test.chat.exception.LoginFailedException;
import com.project.test.chat.login.VO.LoginVO;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public interface LoginService {
    void login(HttpServletResponse req, LoginVO loginVO) throws LoginFailedException;
}
