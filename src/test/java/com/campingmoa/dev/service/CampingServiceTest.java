package com.campingmoa.dev.service;

import com.campingmoa.dev.domain.Authority;
import com.campingmoa.dev.domain.Camping;
import com.campingmoa.dev.domain.Member;
import com.campingmoa.dev.domain.OpenDates;
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
    @Test
    @Rollback(value = false)
    public void 캠핑생성() throws Exception {
        // given
        Member seller = createSeller();
        LocalDate startDate = LocalDate.of(2023,11,10);
        LocalDate endDate = LocalDate.of(2023,11,20);

        Long campingId = campingService.makeCamping(seller, "캠핑이름",startDate, endDate);
        em.flush();
        Camping findCamping = em.find(Camping.class, campingId);

        System.out.println("***************************************");

        Assertions.assertThat(findCamping.getSeller()).isEqualTo(seller);

    }
    private Member createSeller(){
        Member member1 = new Member();
        member1.setEmail("seller1");
        member1.setAuthority(Authority.SELLER);
        member1.setNickname("seller1");
        em.persist(member1);
        return member1;
    }

}