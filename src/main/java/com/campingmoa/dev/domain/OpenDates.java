package com.campingmoa.dev.domain;

import com.campingmoa.dev.exception.NotEnoughStockException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aspectj.apache.bcel.classfile.Module;

import java.time.LocalDate;

import static com.campingmoa.dev.domain.OpenStatus.AVAILABLE;
import static com.campingmoa.dev.domain.OpenStatus.SOLD_OUT;

@Entity
@Getter @Setter
@NoArgsConstructor
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

    public OpenDates(LocalDate openDay) {
        this.openDay = openDay;
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
            throw new NotEnoughStockException("this is unavailable");
        }
        this.status = SOLD_OUT;
    }




}
