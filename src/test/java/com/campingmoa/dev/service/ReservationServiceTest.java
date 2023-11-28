package com.campingmoa.dev.service;

import com.campingmoa.dev.domain.Authority;
import com.campingmoa.dev.domain.Camping;
import com.campingmoa.dev.domain.Member;
import com.campingmoa.dev.exception.NotEnoughStockException;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
class ReservationServiceTest {

    @Autowired
    EntityManager em;
    @Autowired ReservationService reservationService;
    @Autowired CampingService campingService;

    @Test
    @Rollback(value = false)
    public void 예약생성() throws Exception {
        // given
        Member user = createUser();
        Member seller = createSeller();
        Camping camping = createCamping(seller);

        LocalDate startDate = LocalDate.of(2023,11,10);
        LocalDate endDate = LocalDate.of(2023,11,18);
        // when
        reservationService.reserve(user.getId(),camping.getId(),startDate,endDate);

    }


    public void 예약취소 () throws Exception {
        // given


        // when

        // then

    }



    private Member createSeller(){
        Member member1 = new Member();
        member1.setEmail("seller1");
        member1.setAuthority(Authority.SELLER);
        member1.setNickname("seller1");
        em.persist(member1);
        return member1;
    }
    private Member createUser(){
        Member member1 = new Member();
        member1.setEmail("user1");
        member1.setAuthority(Authority.USER);
        member1.setNickname("user1");
        em.persist(member1);
        return member1;
    }

    private Camping createCamping(Member seller){

        LocalDate startDate = LocalDate.of(2023,11,10);
        LocalDate endDate = LocalDate.of(2023,11,20);

        Long campingId = campingService.makeCamping(seller, "캠핑이름",startDate, endDate);

        return campingService.findOne(campingId);
    }

}