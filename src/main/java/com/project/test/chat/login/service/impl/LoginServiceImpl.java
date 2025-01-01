package com.project.test.chat.login.service.impl;

import com.project.test.chat.login.VO.LoginVO;
import com.project.test.chat.login.service.LoginService;
import com.project.test.chat.mapper.login.LoginMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {

    private final LoginMapper loginMapper;

    LoginServiceImpl(LoginMapper loginMapper) {
        this.loginMapper = loginMapper;
    }
    @Override
    public List<LoginVO> login(LoginVO loginVO) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        loginMapper.login(loginVO);

        return List.of();
    }
}
