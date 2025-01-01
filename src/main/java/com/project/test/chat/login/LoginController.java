package com.project.test.chat.login;

import com.project.test.chat.CONST.CONSTS;
import com.project.test.chat.login.VO.LoginVO;
import com.project.test.chat.login.service.impl.LoginServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(CONSTS.REST+"/login")
public class LoginController {

    private final LoginServiceImpl loginService;

    LoginController(LoginServiceImpl loginService){
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(LoginVO loginVO){
        loginService.login(loginVO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
