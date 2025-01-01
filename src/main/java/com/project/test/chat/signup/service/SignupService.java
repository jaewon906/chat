package com.project.test.chat.signup.service;

import com.project.test.chat.signup.VO.SignupVO;

import java.sql.SQLException;

public interface SignupService {
    void signup(SignupVO signupVO) throws SQLException;
}
