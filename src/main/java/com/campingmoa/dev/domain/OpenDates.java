package com.campingmoa.dev.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aspectj.apache.bcel.classfile.Module;

import java.time.LocalDate;

import static com.campingmoa.dev.domain.OpenStatus.AVAILABLE;
import static com.campingmoa.dev.domain.OpenStatus.SOLD_OUT;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OpenDates {
    @Id @GeneratedValue
    @Column(name = "opendates_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "camping_id")
    private Camping camping;
    private LocalDate openDay;
    @Enumerated(EnumType.STRING)
    private OpenStatus status;

    public OpenDates(LocalDate openDay, OpenStatus status) {

        this.openDay = openDay;
        this.status = status;
    }

    /**
     * 캠핑세팅
     */
    public void linkDateToCamping(Camping camping) {
        this.camping = camping;
    }

    /**
     * status 사용가능으로 변경
     */
    public void makeAvailable(){
        this.status = AVAILABLE;
    }
    /**
     * status 품절로 변경
     */
    public void makeSoldOut(){
        if(this.status != AVAILABLE){
            throw new IllegalStateException("this is unavailable");
        }
        this.status = SOLD_OUT;
    }




}
