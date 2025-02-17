package com.base64.gamesback.auth.user.repository.impl;

import com.base64.gamesback.auth.user.dto.DoctorScheduleResponse;
import com.base64.gamesback.auth.user.entity.Doctor;
import com.base64.gamesback.auth.user.entity.DoctorSchedule;
import com.base64.gamesback.auth.user.entity.DoctorSchedule_;
import com.base64.gamesback.auth.user.entity.Doctor_;
import com.base64.gamesback.auth.user.repository.DoctorScheduleRepositoryCustom;
import com.base64.gamesback.common.criteria.Criteria;
import com.base64.gamesback.common.criteria.CriteriaPredicate;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.UUID;

@Slf4j
public class DoctorScheduleRepositoryImpl implements DoctorScheduleRepositoryCustom {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<DoctorScheduleResponse> getDoctorScheduleByDoctorId(UUID doctorId, Criteria criteria) {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        List<DoctorScheduleResponse> result = null;

        try {
            CriteriaQuery<DoctorScheduleResponse> cq = cb.createQuery(DoctorScheduleResponse.class);
            Root<DoctorSchedule> root = cq.from(DoctorSchedule.class);
            Join<DoctorSchedule, Doctor> doctorScheduleDoctorJoin = root.join(DoctorSchedule_.doctor, JoinType.INNER);

            cq.select(
                    cb.construct(
                            DoctorScheduleResponse.class,
                            root.get(DoctorSchedule_.doctorScheduleId),
                            root.get(DoctorSchedule_.startDate),
                            root.get(DoctorSchedule_.endDate),
                            root.get(DoctorSchedule_.available)
                    )
            );
            cq.where(cb.equal(doctorScheduleDoctorJoin.get(Doctor_.doctorId), doctorId));
            CriteriaPredicate<DoctorSchedule, DoctorScheduleResponse> predicate = new CriteriaPredicate<>(cb);
            TypedQuery<DoctorScheduleResponse> query = manager.createQuery(predicate.convert(cq, criteria, root));
            result = query.getResultList();
        } catch (Exception ex) {
            log.error("error en la consulta criteria getDoctorScheduleByDoctorId {}", ex.getMessage());
        }
        return result;
    }
}
