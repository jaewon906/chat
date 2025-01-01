package com.project.test.chat.signup.VO;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SignupVO {
    @NonNull
    private String userId;
    @NonNull
    private String password;
    @NonNull
    private String email;
}
