package com.project.test.chat.signup.service.Impl;

import com.project.test.chat.exception.DuplicatedUserInfoException;
import com.project.test.chat.mapper.signup.SignupMapper;
import com.project.test.chat.signup.VO.SignupVO;
import com.project.test.chat.signup.service.SignupService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class SignupServiceImpl implements SignupService {

    private final SignupMapper signupMapper;
    public SignupServiceImpl(SignupMapper signupMapper) {
        this.signupMapper = signupMapper;
    }
    @Override
    public void signup(SignupVO signupVO) throws SQLException, DuplicatedUserInfoException {
        List<SignupVO> checkValidation = signupMapper.checkValidation(signupVO);

        if (!checkValidation.isEmpty())
            throw new DuplicatedUserInfoException("This user is already joined!!");

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encoded = encoder.encode(signupVO.getPassword());
        signupVO.setPassword(encoded);

        int result =  signupMapper.insertUserInfo(signupVO);
        if(result != 1) {
            throw new SQLException();
        }
    }
}
