package com.campingmoa.dev.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

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

    private LocalDateTime startDate; //예약시작일
    private LocalDateTime endDate; //예약종료일

    @Enumerated(EnumType.STRING)
    private ReservationStatus status;



}
