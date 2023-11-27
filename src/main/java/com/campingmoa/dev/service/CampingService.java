package com.campingmoa.dev.service;

import com.campingmoa.dev.domain.Camping;
import com.campingmoa.dev.domain.Member;
import com.campingmoa.dev.domain.OpenDates;
import com.campingmoa.dev.repository.CampingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CampingService {

    private final CampingRepository campingRepository;

    @Transactional
    public Long makeCamping(Member member, LocalDate startDate, LocalDate endDate){
        List<OpenDates> opendateList = new ArrayList<>();
        LocalDate target = startDate;
        while(!(target.isEqual(endDate))){
            opendateList.add(new OpenDates(target));
            target = target.plusDays(1);
        }
        Camping camping = Camping.createCamping(member,opendateList);

        campingRepository.save(camping);
        return camping.getId();
    }

    public List<Camping> findCamping(){
        return campingRepository.findAll();
    }
    public Camping findOne(Long campingId){
        return campingRepository.findOne(campingId);
    }
}
