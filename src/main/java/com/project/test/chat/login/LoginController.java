package com.project.test.chat.login;

import com.project.test.chat.CONST.CONSTS;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(CONSTS.REST+"/login")
public class LoginController {

    @PostMapping("/login")
    public ResponseEntity<String> login(){
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
