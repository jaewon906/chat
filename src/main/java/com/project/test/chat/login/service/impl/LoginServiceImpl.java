package com.project.test.chat.login.service.impl;

import com.project.test.chat.common.TokenGeneration;
import com.project.test.chat.exception.LoginFailedException;
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
    public List<LoginVO> login(LoginVO loginVO) throws LoginFailedException {
        List<LoginVO> userInfo = loginMapper.getUserInfo(loginVO);
        if(userInfo.isEmpty()) throw new LoginFailedException("Please check your ID or Password");

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        boolean isPasswordCorrect = encoder.matches(loginVO.getPassword(), userInfo.get(0).getPassword());
        if(!isPasswordCorrect) throw new LoginFailedException("Please check your ID or Password");

        TokenGeneration.TokenGenerationBuilder tokenGenerationBuilder = TokenGeneration.builder();
        tokenGenerationBuilder.build();

        return List.of();
    }
}
