package com.campingmoa.dev.service;

import com.campingmoa.dev.domain.Camping;
import com.campingmoa.dev.repository.CampingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CampingService {

    private final CampingRepository campingRepository;

    @Transactional
    public void saveCamping(Camping camping){
        campingRepository.save(camping);
    }

    public List<Camping> findCamping(){
        return campingRepository.findAll();
    }
    public Camping findOne(Long campingId){
        return campingRepository.findOne(campingId);
    }
}
