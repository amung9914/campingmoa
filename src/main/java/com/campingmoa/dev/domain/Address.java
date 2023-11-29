package com.campingmoa.dev.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable @EqualsAndHashCode
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {
    @Column(length = 100)
    private String roadAddress; // 도로명주소
    @Column(length = 10)
    private String city; //ex 서울시
    @Column(length = 10)
    private String sigungu; //ex 강남구
    private String lat; // 위도
    private String lng; // 경도

    //비즈니스적인 메소드
    public String fullAddress(){
        return getRoadAddress()+" "+getCity()+" "+getSigungu()+" "+getLat()+" "+getLng();
    }

    //생성메소드
    public Address(String roadAddress,String city, String sigungu, String lat, String lng) {
        this.roadAddress = roadAddress;
        this.city = city;
        this.sigungu = sigungu;
        this.lat = lat;
        this.lng = lng;
    }

    public Address changeAddress(String roadAddress,String city, String sigungu, String lat, String lng){
        this.roadAddress = roadAddress;
        this.city = city;
        this.sigungu = sigungu;
        this.lat = lat;
        this.lng = lng;
        return this;
    }

}
