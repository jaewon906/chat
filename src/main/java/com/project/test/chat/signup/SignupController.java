package com.project.test.chat.signup;

import com.project.test.chat.CONST.CONSTS;
import com.project.test.chat.exception.DuplicatedUserInfoException;
import com.project.test.chat.signup.VO.SignupVO;
import com.project.test.chat.signup.service.Impl.SignupServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@Transactional
@RequestMapping(CONSTS.REST + "/signup")
public class SignupController {

    private final SignupServiceImpl signupService;

    public SignupController(SignupServiceImpl signupService) {
        this.signupService = signupService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupVO signupVO) throws SQLException, DuplicatedUserInfoException {
        ResponseEntity<String> res = new ResponseEntity<>(HttpStatus.OK);
        signupService.signup(signupVO);
        return res;
    }
}
