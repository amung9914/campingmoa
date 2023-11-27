package com.campingmoa.dev.domain;

import com.campingmoa.dev.exception.NotEnoughStockException;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    private int price; //1박당 가격
    // camping 삭제 시 예약있으면 삭제 못하게 조치 필 요 **************
    @OneToMany(mappedBy = "camping", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations = new ArrayList<>();
    @OneToMany(mappedBy = "camping")
    private List<OpenDates> openDatesList = new ArrayList<>();

    //연관관계 편의 메서드
    public void addOpenDates(OpenDates openDates){
        openDatesList.add(openDates);
        openDates.setCamping(this);
        openDates.setStatus(OpenStatus.AVAILABLE);
    }

    //==생성메서드==//
    public static Camping createCamping(Member member, List<OpenDates> openDates){
        Camping camping = new Camping();
        camping.setSeller(member);
        for(OpenDates date : openDates){
            camping.addOpenDates(date);
        }
        return camping;
    }

    /**
     * 예약 가능 여부 확인
     */
    public void isAvailable(LocalDate startDate,LocalDate endDate){

        //범위내에서 모든 날짜가 가능한지 확인한다.
        for(OpenDates openDate : openDatesList){
            LocalDate target = startDate;
            while(!(target.isEqual(endDate))){
                if(openDate.getStatus().equals(OpenStatus.SOLD_OUT)){
                    throw new IllegalStateException("이미 예약이 마감되었습니다.");
                }
                target = target.plusDays(1);
            }
        }
    }

}
