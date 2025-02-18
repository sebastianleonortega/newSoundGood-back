package com.base64.gamesback.auth.user.repository.impl;

import com.base64.gamesback.auth.user.dto.TestResponse;
import com.base64.gamesback.auth.user.entity.Person;
import com.base64.gamesback.auth.user.entity.Person_;
import com.base64.gamesback.auth.user.entity.Test;
import com.base64.gamesback.auth.user.entity.Test_;
import com.base64.gamesback.auth.user.repository.TestRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.extern.slf4j.Slf4j;
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
                            root.get(Test_.resultLeft),
                            root.get(Test_.resultRight),
                            root.get(Test_.resultNumeric),
                            root.get(Test_.testDate)
                    )
            );
            cq.where(cb.equal(personJoin.get(Person_.personId), personId));

            TypedQuery<TestResponse> query = manager.createQuery(cq);
            result = query.getResultList();
            return result;

        } catch (Exception ex) {
            log.error("error en la consulta criteria getTestByPerson {}", ex.getMessage());
        }
        manager.close();
        return result;
    }
}
