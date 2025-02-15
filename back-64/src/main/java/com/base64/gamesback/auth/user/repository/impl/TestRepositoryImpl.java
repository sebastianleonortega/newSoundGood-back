package com.base64.gamesback.auth.user.repository.impl;

import com.base64.gamesback.auth.user.dto.TestResponse;
import com.base64.gamesback.auth.user.dto.UserDoctorResponse;
import com.base64.gamesback.auth.user.dto.UserPatientResponse;
import com.base64.gamesback.auth.user.dto.UserResponseDto;
import com.base64.gamesback.auth.user.entity.*;
import com.base64.gamesback.auth.user.repository.TestRepositoryCustom;
import com.base64.gamesback.auth.user.repository.UserRepositoryCustom;
import com.base64.gamesback.common.criteria.Criteria;
import com.base64.gamesback.common.criteria.CriteriaPredicate;
import com.base64.gamesback.documentType.entity.DocumentType;
import com.base64.gamesback.documentType.entity.DocumentType_;
import com.base64.gamesback.genderType.entity.GenderType;
import com.base64.gamesback.genderType.entity.GenderType_;
import com.base64.gamesback.hearing_loss.dto.HearingLossResponseDto;
import com.base64.gamesback.hearing_loss.entity.HearingLoss;
import com.base64.gamesback.hearing_loss.entity.HearingLoss_;
import com.base64.gamesback.speciality.dto.SpecialityResponseDto;
import com.base64.gamesback.speciality.entity.Speciality;
import com.base64.gamesback.speciality.entity.Speciality_;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@Slf4j
public class TestRepositoryImpl implements TestRepositoryCustom {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<TestResponse> getTestByPerson(UUID personId) {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        List<TestResponse> result = null;

        try {
            CriteriaQuery<TestResponse> cq = cb.createQuery(TestResponse.class);

            Root<Test> root = cq.from(Test.class);
            Join<Test, Person> personJoin = root.join(Test_.person, JoinType.INNER);

            cq.select(
                    cb.construct(
                            TestResponse.class,
                            personJoin.get(Person_.personName),
                            personJoin.get(Person_.personLastName),
                            root.get(Test_.testId),
                            root.get(Test_.testType),
                            root.get(Test_.result),
                            root.get(Test_.testDate)
                    )
            );
            cq.where(cb.equal(personJoin.get(Person_.personId), personId));

            TypedQuery<TestResponse> query = manager.createQuery(cq);
            result = query.getResultList();
            return result;

        }catch (Exception ex) {
            log.error("error en la consulta criteria getTestByPerson {}", ex.getMessage());
        }
        manager.close();
        return result;
    }
}
