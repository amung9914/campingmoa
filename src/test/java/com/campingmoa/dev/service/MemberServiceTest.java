package com.campingmoa.dev.service;

import com.campingmoa.dev.domain.Authority;
import com.campingmoa.dev.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@RunWith(SpringRunner.class)
class MemberServiceTest {

    @Autowired MemberService memberService;

    @Test
    @Rollback(value = false)
    public void 중복회원예외() throws Exception {
        // given
        memberService.join("email1","1111","nick1","010");
        memberService.joinSeller("email2","1111","nick2","010");
/*
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            memberService.join(member2);
        });
        assertTrue(exception.getMessage().contains("이미 존재하는 닉네임입니다."));
*/


        // when

        // then

    }
}