package com.campingmoa.dev.domain;

import com.campingmoa.dev.exception.NotEnoughStockException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Camping extends BaseTime {

    @Id @GeneratedValue
    @Column(name= "camping_id")
    private Long id;
    @Column(length = 75, nullable = false)
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member seller;
    @Column(length = 30, nullable = false)
    private int price;
    @OneToMany(mappedBy = "camping")
    private List<Reservation> reservations = new ArrayList<>(); // 캠핑주 예약 확인용

    private LocalDateTime startDate; // 캠핑 시작일
    private LocalDateTime endDate; //캠핑 종료일


    @Enumerated(EnumType.STRING)
    private CampingStatus status; // stock == 1 이라서 status 표시


    /**
     * status 사용가능으로 변경
     */
    public void makeAvailable(){
        this.status = CampingStatus.AVAILABLE;
    }
    /**
     * status 품절로 변경
     */
    public void makeSoldOut(){
        if(this.status != CampingStatus.AVAILABLE){
            throw new NotEnoughStockException("this is unavailable");
        }
        this.status = CampingStatus.SOLD_OUT;
    }

}
