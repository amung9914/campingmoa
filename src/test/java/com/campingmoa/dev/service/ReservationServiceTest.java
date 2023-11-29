package com.campingmoa.dev.service;

import com.campingmoa.dev.domain.*;
import com.campingmoa.dev.repository.ReservationRepository;
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
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired MemberService memberService;

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

    @Test
    @Rollback(value = false)
    public void 예약취소 () throws Exception {
        // given
        Member user = createUser();
        Member seller = createSeller();
        Camping camping = createCamping(seller);

        LocalDate startDate = LocalDate.of(2023,11,10);
        LocalDate endDate = LocalDate.of(2023,11,18);
        // when
        Long resvId = reservationService.reserve(user.getId(), camping.getId(), startDate, endDate);

        // when
        reservationService.cancel(resvId);

        Reservation findResv = reservationRepository.findOne(resvId);


        // then
        assertEquals(ReservationStatus.CANCEL, findResv.getStatus(), "예약취소시 예약상태는 CANCEL이다");
        //날짜상태도 AVAILABLE확인완료.
    }



    private Member createSeller(){

        Long memberId = memberService.joinSeller("email2", "1111", "nick2", "010");
        em.flush();
        return em.find(Member.class, memberId);
    }
    private Member createUser(){
        Long memberId = memberService.join("email1", "1111", "nick1", "010");
        em.flush();
        return em.find(Member.class, memberId);
    }

    private Camping createCamping(Member seller){

        LocalDate startDate = LocalDate.of(2023,11,10);
        LocalDate endDate = LocalDate.of(2023,11,20);
        Address address = new Address("도로명주소","서울","강남구","위도","경도");

        Long campingId = campingService.makeCamping(seller, "캠핑이름",address,startDate, endDate);

        return campingService.findOne(campingId);
    }

}