package com.project.test.chat.signup.service.Impl;

import com.project.test.chat.mapper.signup.SignupMapper;
import com.project.test.chat.signup.VO.SignupVO;
import com.project.test.chat.signup.service.SignupService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class SignupServiceImpl implements SignupService {

    private final SignupMapper signupMapper;
    public SignupServiceImpl(SignupMapper signupMapper) {
        this.signupMapper = signupMapper;
    }
    @Override
    public void signup(SignupVO signupVO) throws SQLException {
        int result =  signupMapper.insertUserInfo(signupVO);
        if(result != 1) {
            throw new SQLException();
        }
    }
}
