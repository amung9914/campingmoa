package com.campingmoa.dev.service;

import com.campingmoa.dev.domain.*;
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


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CampingServiceTest {
    @Autowired
    EntityManager em;
    @Autowired
    CampingService campingService;
    @Autowired MemberService memberService;
    @Test
    @Rollback(value = false)
    public void 캠핑생성() throws Exception {
        // given

        Long sellerId = memberService.joinSeller("email2", "1111", "nick2", "010");
        em.flush();
        Member seller = em.find(Member.class, sellerId);
        LocalDate startDate = LocalDate.of(2023,11,10);
        LocalDate endDate = LocalDate.of(2023,11,20);

        Address address = new Address("도로명주소","서울","강남구","위도","경도");
        Long campingId = campingService.makeCamping(seller, "캠핑이름",address, startDate, endDate);
        em.flush();
        Camping findCamping = em.find(Camping.class, campingId);

        System.out.println("***************************************");

        Assertions.assertThat(findCamping.getSeller()).isEqualTo(seller);

    }

}