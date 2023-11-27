package com.campingmoa.dev.service;

import com.campingmoa.dev.domain.Camping;
import com.campingmoa.dev.domain.Member;
import com.campingmoa.dev.domain.OpenDates;
import com.campingmoa.dev.domain.Reservation;
import com.campingmoa.dev.repository.CampingRepository;
import com.campingmoa.dev.repository.MemberRepository;
import com.campingmoa.dev.repository.OpenDatesRepository;
import com.campingmoa.dev.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final MemberRepository memberRepository;
    private final CampingRepository campingRepository;
    private final OpenDatesRepository openDatesRepository;

    /**
     * 예약
     */
    @Transactional
    public Long reserve(Long memberId, Long campingId, LocalDate startDate, LocalDate endDate) {
        //엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Camping camping = campingRepository.findOne(campingId);

        //예약 가능한지 확인
        camping.isAvailable(startDate, endDate);

        // 날짜 예약 처리
        List<OpenDates> openDatesList = openDatesRepository.findByCamping(campingId);
        for (OpenDates openDate : openDatesList) {
            LocalDate target = openDate.getOpenDay();
            if(target.isAfter(startDate)||target.isBefore(endDate)
                    ||target.isEqual(startDate)||target.isEqual(endDate)){
                openDate.makeSoldOut();
            }
        }

        //예약 생성
        Reservation reservation = Reservation.createReservation(member, camping, startDate, endDate);
        //예약 저장
        reservationRepository.save(reservation);
        return reservation.getId();
    }

        //예약취소
        @Transactional
        public void cancel(Long campingId,LocalDate startDate, LocalDate endDate){

            LocalDate target = startDate;
            while(!(target.isEqual(endDate))){
                target = target.plusDays(1);
            }
            아직 모르겠다^^
        }


}
