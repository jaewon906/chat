package com.project.test.chat.login.service.impl;

import com.project.test.chat.common.TokenGeneration;
import com.project.test.chat.exception.LoginFailedException;
import com.project.test.chat.login.VO.LoginVO;
import com.project.test.chat.login.service.LoginService;
import com.project.test.chat.mapper.login.LoginMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

    private final LoginMapper loginMapper;
    private final TokenGeneration tokenGeneration;

    LoginServiceImpl(LoginMapper loginMapper, TokenGeneration tokenGeneration) {
        this.loginMapper = loginMapper;
        this.tokenGeneration = tokenGeneration;
    }
    @Override
    public void login(HttpServletResponse res, LoginVO loginVO) throws LoginFailedException {
        // 1. check user info
        List<LoginVO> userInfo = loginMapper.getUserInfo(loginVO);
        if(userInfo.isEmpty()) throw new LoginFailedException("Please check your ID or Password");

        // 2. decrypt DB password and compare requested rawPassword
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        boolean isPasswordCorrect = encoder.matches(loginVO.getPassword(), userInfo.get(0).getPassword());
        if(!isPasswordCorrect) throw new LoginFailedException("Please check your ID or Password");

        // 3. get user authorization
        List<LoginVO> userAuth = loginMapper.getUserAuth(loginVO);

        // 4. generate accessToken and refreshToken;
        res.addCookie(tokenGeneration.generateAccessToken(userAuth.get(0)));
        res.addCookie(tokenGeneration.generateRefreshToken(userAuth.get(0)));
    }
}
