package com.campingmoa.dev.repository;

import com.campingmoa.dev.domain.Camping;
import com.campingmoa.dev.domain.OpenDates;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OpenDatesRepository {

    private final EntityManager em;

    public void save(OpenDates openDates){
        em.persist(openDates);
    }

    public OpenDates findOne(Long id){
        return em.find(OpenDates.class, id);
    }

    public List<OpenDates> findAll(){
        return em.createQuery("select o from OpenDates o", OpenDates.class)
                .getResultList();
    }

    public List<OpenDates> findByCamping(Long campingId){
        return em.createQuery("select o from OpenDates o where o.camping =:camping", OpenDates.class)
                .setParameter("camping",campingId)
                .getResultList();
    }

}
