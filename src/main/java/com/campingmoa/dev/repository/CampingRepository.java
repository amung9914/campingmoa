package com.campingmoa.dev.repository;

import com.campingmoa.dev.domain.Camping;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CampingRepository {

    private final EntityManager em;

    public void save(Camping camping){
        if(camping.getId() == null){ //완전히 새로 생성하는 객체인 경우
            em.persist(camping);
        }else{
            em.merge(camping);
        }
    }

    public Camping findOne(Long id){
        return em.find(Camping.class, id);
    }

    public List<Camping> findAll(){
        return em.createQuery("select c from Camping c", Camping.class)
                .getResultList();
    }

}
