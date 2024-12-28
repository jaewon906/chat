package com.project.test.chat.login;

import com.project.test.chat.CONST.CONSTS;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(CONSTS.REST)
public class LoginController {

    @GetMapping("/checkAuthentication")
    public ResponseEntity<String> checkAuthentication(){
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
