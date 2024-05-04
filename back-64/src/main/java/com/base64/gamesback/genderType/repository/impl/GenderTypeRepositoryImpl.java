package com.base64.gamesback.genderType.repository.impl;

import com.base64.gamesback.genderType.dto.GenderTypeResponse;
import com.base64.gamesback.genderType.entity.GenderType;
import com.base64.gamesback.genderType.entity.GenderType_;
import com.base64.gamesback.genderType.repository.GenderTypeRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class GenderTypeRepositoryImpl implements GenderTypeRepositoryCustom {

    @PersistenceContext
    EntityManager em;


    @Override
    public List<GenderTypeResponse> getAllGenderTypes() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        List<GenderTypeResponse> result = null;

        try {

            CriteriaQuery<GenderTypeResponse> cq = cb.createQuery(GenderTypeResponse.class);

            Root<GenderType> genderTypeRoot = cq.from(GenderType.class);

            cq.select(cb.construct(
               GenderTypeResponse.class,
               genderTypeRoot.get(GenderType_.genderId),
               genderTypeRoot.get(GenderType_.code)
            ));

            TypedQuery<GenderTypeResponse> query = em.createQuery(cq);
            result = query.getResultList();
        }catch (Exception ex){
            log.error("Error en la consulta Criteria getAllGenderTypes {}", ex.getMessage());
        }finally {
            em.close();
        }
        return result;
    }
}
