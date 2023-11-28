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

        //예약날짜 존재여부 확인
        camping.isValid(startDate, endDate);
        //예약 가능한지 확인
        camping.isAvailable(startDate, endDate);

        // 날짜 예약 처리
        List<OpenDates> openDatesList = openDatesRepository.findByCamping(camping);
        for (OpenDates openDate : openDatesList) {
            LocalDate target = openDate.getOpenDay();
            if(target.isEqual(startDate)||target.isAfter(startDate)&&target.isBefore(endDate)
                    ||target.isEqual(endDate)){
                System.out.println(target);
                openDate.makeSoldOut(target);
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
        public void cancel(Long resvId){
            //예약 상태확인
            Reservation reservation = reservationRepository.findOne(resvId);
            reservation.cancel();
            //캠핑상품 예약일 확인
            List<OpenDates> OpenDatesList = openDatesRepository.findByCamping(reservation.getCamping());

            LocalDate startDate = reservation.getStartDate();
            LocalDate endDate = reservation.getEndDate();
            // 캠핑 날짜 상태 복구
            LocalDate target = startDate;
            for(OpenDates dateData : OpenDatesList){
                if(dateData.getOpenDay().equals(target)){
                    dateData.makeAvailable();
                }
                    target = target.plusDays(1);
            }

        }


}
