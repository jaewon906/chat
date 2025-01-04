package com.project.test.chat.login.VO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class LoginVO {
    private String userId;
    private String password;
    private String roleCd;
    private String roleNm;
}
