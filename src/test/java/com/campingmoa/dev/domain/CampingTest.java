package com.campingmoa.dev.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CampingTest {

/*
    @Test
    public void 가능확인() throws Exception {
        // given
        OpenDates openDates1 = new OpenDates(LocalDate.parse("2023-11-10"),OpenStatus.AVAILABLE);
        OpenDates openDates2 = new OpenDates(LocalDate.parse("2023-11-11"),OpenStatus.AVAILABLE);
        OpenDates openDates3 = new OpenDates(LocalDate.parse("2023-11-10"),OpenStatus.AVAILABLE);

        List<OpenDates> list = new ArrayList<>();
        list.add(openDates1);
        list.add(openDates2);
        list.add(openDates3);
        list.add(openDates4);
        list.add(openDates5);
        list.add(openDates6);
        list.add(openDates7);
        Camping camping = Camping.createCamping(new Member(), "test", list);
        List<OpenDates> openDatesList = camping.getOpenDatesList();

        // when
        LocalDate startDate = LocalDate.parse("2023-11-10");
        LocalDate endDate = LocalDate.parse("2023-11-19");
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




        for(OpenDates openDate : list){
            LocalDate target = openDate.getOpenDay();
            System.out.println("*************************");
            System.out.println(target);
            if(target.isEqual(startDate)||target.isAfter(startDate)
                    &&target.isBefore(endDate) ||target.isEqual(endDate)) {

                if (openDate.getStatus().equals(OpenStatus.SOLD_OUT)) {
                    throw new IllegalStateException("이미 예약이 마감되었습니다.");
                }
                    //기간내에 예약 불가는 확인 가능
                    // 근데 유효한 기간이 아닌데 선택했을때 예외가 터졌으면 좋겠음.
            }

        }
        // then
        }
        */


}