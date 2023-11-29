package com.campingmoa.dev.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
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
    @OneToMany(mappedBy = "camping")
    private List<Reservation> reservations = new ArrayList<>();
    @OneToMany(mappedBy = "camping", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OpenDates> openDatesList = new ArrayList<>();
    @Embedded
    private Address address;

    //연관관계 편의 메서드
    public void addOpenDates(OpenDates openDates){
        openDates.linkDateToCamping(this);
        openDatesList.add(openDates);
    }

    //==생성메서드==//
    public static Camping createCamping(Member member,String name,Address address,List<OpenDates> openDates){
        Camping camping = new Camping();
        camping.seller = member;
        camping.name = name;
        camping.address = address;
        for(OpenDates date : openDates){
            camping.addOpenDates(date);
        }
        return camping;
    }

    /**
     * 예약 가능 여부 확인(이미 판매되었는지)
     */
    public void isAvailable(LocalDate startDate,LocalDate endDate){

        //범위내에서 모든 날짜가 가능한지 확인한다.
        for(OpenDates openDate : openDatesList){
            LocalDate target = openDate.getOpenDay();
            if(target.isEqual(startDate)||target.isAfter(startDate)
                    &&target.isBefore(endDate) ||target.isEqual(endDate)) {

                if (openDate.getStatus().equals(OpenStatus.SOLD_OUT)) {
                    throw new IllegalStateException("이미 예약이 마감되었습니다.");
                }

            }
        }
    }

    /**
     * 날짜가 존재하는지 확인
     */
    public void isValid(LocalDate startDate,LocalDate endDate){
        LocalDate temp = startDate;
        List<LocalDate> cannotDayList = new ArrayList<>();

        // 선택할 수 없는 날짜는 리스트에 담는다.

        exit : while(!(temp.isEqual(endDate))){
            // 날짜가 있나요?
            for (OpenDates dateEntity : openDatesList) {
                if(dateEntity.getOpenDay().equals(temp)){
                    temp = temp.plusDays(1);
                    continue exit;
                }
            }
            cannotDayList.add(temp);
            temp = temp.plusDays(1);
        }

        if(!cannotDayList.isEmpty()){
            throw new IllegalStateException("선택하신 날짜는 사용이 불가합니다.");
        }
    }
}
