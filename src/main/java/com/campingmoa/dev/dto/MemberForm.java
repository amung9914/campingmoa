package com.campingmoa.dev.dto;

import lombok.Data;

@Data
public class MemberForm {
    private String email;
    private String password;
    private String nickname;
}
