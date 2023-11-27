package com.campingmoa.dev.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static java.time.temporal.ChronoUnit.DAYS;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reservation extends BaseTime{
    @Id @GeneratedValue
    @Column(name = "resv_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "camping_id")
    private Camping camping;
    @Column(nullable = false)
    private LocalDate startDate; //예약시작일
    @Column(nullable = false)
    private LocalDate endDate; //예약종료일

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    private int resPrice; //예약가격(1박가격*기간)


    //==생성 메서드==//
    public static Reservation createReservation(Member member, Camping camping, LocalDate startDate, LocalDate endDate){
        Reservation reservation = new Reservation();
        reservation.setMember(member);
        reservation.setCamping(camping);
        reservation.setStartDate(startDate);
        reservation.setEndDate(endDate);
        reservation.setStatus(ReservationStatus.RESERVED);
        long days = DAYS.between(startDate,endDate); // 시작일과 종료일 차이
        reservation.setResPrice((int) (camping.getPrice()*days));

        return reservation;
    }

    //==비즈니스 로직==//
    public void cancel(){
        if(this.status == ReservationStatus.COMPLETE){
            throw new IllegalStateException("이미 종료된 예약은 취소가 불가능합니다.");
        }
        this.setStatus(ReservationStatus.CANCEL);

    }



}
