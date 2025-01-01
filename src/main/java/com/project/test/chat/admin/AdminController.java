package com.project.test.chat.admin;

import com.project.test.chat.CONST.CONSTS;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(CONSTS.REST+"/admin")
public class AdminController {

    @GetMapping("/")
    public ResponseEntity<String> checkAdmin(){
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
