package com.base64.gamesback.auth.user.repository.impl;

import com.base64.gamesback.auth.user.dto.UserDoctorResponse;
import com.base64.gamesback.auth.user.dto.UserPatientResponse;
import com.base64.gamesback.auth.user.entity.*;
import com.base64.gamesback.auth.user.repository.UserRepositoryCustom;
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
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@Slf4j
public class UserRepositoryImpl implements UserRepositoryCustom {

    @PersistenceContext
    private EntityManager manager;


    @Override
    public UserDoctorResponse getUserDoctorById(UUID userId) {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        UserDoctorResponse result = null;

        try {
            CriteriaQuery<UserDoctorResponse> cq = cb.createQuery(UserDoctorResponse.class);

            Root<User> root = cq.from(User.class);
            Join<User, Doctor> userDoctorJoin = root.join(User_.doctor, JoinType.INNER);
            Join<Doctor, DocumentType> doctorDocumentTypeJoin = userDoctorJoin.join(Doctor_.documentType, JoinType.INNER);
            Join<Doctor, GenderType> doctorGenderTypeJoin = userDoctorJoin.join(Doctor_.genderType, JoinType.INNER);

            cq.select(cb.construct(
                    UserDoctorResponse.class,
                    root.get(User_.userId),
                    root.get(User_.userName),
                    root.get(User_.administrator),
                    userDoctorJoin.get(Doctor_.name),
                    userDoctorJoin.get(Doctor_.lastName),
                    userDoctorJoin.get(Doctor_.phone),
                    userDoctorJoin.get(Doctor_.address),
                    doctorDocumentTypeJoin.get(DocumentType_.documentTypeId),
                    doctorDocumentTypeJoin.get(DocumentType_.code),
                    userDoctorJoin.get(Doctor_.document),
                    doctorGenderTypeJoin.get(GenderType_.genderId),
                    doctorGenderTypeJoin.get(GenderType_.code),
                    userDoctorJoin.get(Doctor_.email),
                    userDoctorJoin.get(Doctor_.image),
                    userDoctorJoin.get(Doctor_.description),
                    userDoctorJoin.get(Doctor_.latitude),
                    userDoctorJoin.get(Doctor_.longitude)
            ));
            cq.distinct(true);

            cq.where(
                    cb.equal(root.get(User_.userId), userId)
            );
            TypedQuery<UserDoctorResponse> query = manager.createQuery(cq);
            UserDoctorResponse userDoctorResponse = query.getSingleResult();
            userDoctorResponse.addResponseSpeciality(
                    this.getAllSpecialitiesByDoctorId(userId));
            result = userDoctorResponse;
        } catch (Exception ex) {
            log.error("error en la consulta criteria getUserDoctorById {}", ex.getMessage());
        }
        manager.close();
        return result;
    }

    @Override
    public UserPatientResponse getUserPatientById(UUID userId) {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        UserPatientResponse result = null;

        try {
            CriteriaQuery<UserPatientResponse> cq = cb.createQuery(UserPatientResponse.class);

            Root<User> root = cq.from(User.class);
            Join<User, Person> userPersonJoin = root.join(User_.person, JoinType.INNER);
            Join<Person, DocumentType> personDocumentTypeJoin = userPersonJoin.join(Person_.documentType, JoinType.INNER);
            Join<Person, GenderType> personGenderTypeJoin = userPersonJoin.join(Person_.genderType, JoinType.INNER);

            cq.select(cb.construct(
                    UserPatientResponse.class,
                    root.get(User_.userId),
                    root.get(User_.userName),
                    root.get(User_.administrator),
                    root.get(User_.profileImage),
                    userPersonJoin.get(Person_.personName),
                    userPersonJoin.get(Person_.personLastName),
                    personDocumentTypeJoin.get(DocumentType_.documentTypeId),
                    personDocumentTypeJoin.get(DocumentType_.code),
                    userPersonJoin.get(Person_.personDocument),
                    personGenderTypeJoin.get(GenderType_.genderId),
                    personGenderTypeJoin.get(GenderType_.code),
                    userPersonJoin.get(Person_.personPhone),
                    userPersonJoin.get(Person_.personAddress),
                    userPersonJoin.get(Person_.personEmail),
                    userPersonJoin.get(Person_.previousTreatments)
            ));
            cq.distinct(true);

            cq.where(
                    cb.equal(root.get(User_.userId), userId)
            );
            TypedQuery<UserPatientResponse> query = manager.createQuery(cq);
            UserPatientResponse userPatientResponse = query.getSingleResult();
            userPatientResponse.addResponseHearingLosses(
                    this.getAllHearingLossesByPersonId(userId)
            );
            result = userPatientResponse;
        } catch (Exception ex) {
            log.error("error en la consulta criteria getUserPatientById {}", ex.getMessage());
        }
        manager.close();
        return result;
    }

    @Override
    public List<UserDoctorResponse> getAllUserDoctors() {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        List<UserDoctorResponse> result = null;

        try {
            CriteriaQuery<UserDoctorResponse> cq = cb.createQuery(UserDoctorResponse.class);

            Root<User> root = cq.from(User.class);
            Join<User, Doctor> userDoctorJoin = root.join(User_.doctor, JoinType.INNER);
            Join<Doctor, DocumentType> doctorDocumentTypeJoin = userDoctorJoin.join(Doctor_.documentType, JoinType.INNER);
            Join<Doctor, GenderType> doctorGenderTypeJoin = userDoctorJoin.join(Doctor_.genderType, JoinType.INNER);

            cq.select(cb.construct(
                    UserDoctorResponse.class,
                    root.get(User_.userId),
                    root.get(User_.userName),
                    root.get(User_.administrator),
                    userDoctorJoin.get(Doctor_.name),
                    userDoctorJoin.get(Doctor_.lastName),
                    userDoctorJoin.get(Doctor_.phone),
                    userDoctorJoin.get(Doctor_.address),
                    doctorDocumentTypeJoin.get(DocumentType_.documentTypeId),
                    doctorDocumentTypeJoin.get(DocumentType_.code),
                    userDoctorJoin.get(Doctor_.document),
                    doctorGenderTypeJoin.get(GenderType_.genderId),
                    doctorGenderTypeJoin.get(GenderType_.code),
                    userDoctorJoin.get(Doctor_.email),
                    userDoctorJoin.get(Doctor_.image),
                    userDoctorJoin.get(Doctor_.description),
                    userDoctorJoin.get(Doctor_.latitude),
                    userDoctorJoin.get(Doctor_.longitude)
            ));
            cq.distinct(true);

            TypedQuery<UserDoctorResponse> query = manager.createQuery(cq);
            List<UserDoctorResponse> userDoctorResponse = query.getResultList();
            userDoctorResponse.forEach(
                    userDoctorResponse1 -> userDoctorResponse1.addResponseSpeciality(
                            this.getAllSpecialitiesByDoctorId(userDoctorResponse1.getUserId())
                    )
            );
            result = userDoctorResponse;
        } catch (Exception ex) {
            log.error("error en la consulta criteria getAllUserDoctors {}", ex.getMessage());
        }
        manager.close();
        return result;
    }

    @Override
    public List<UserPatientResponse> getAllUserPatients() {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        List<UserPatientResponse> result = null;

        try {
            CriteriaQuery<UserPatientResponse> cq = cb.createQuery(UserPatientResponse.class);

            Root<User> root = cq.from(User.class);
            Join<User, Person> userPersonJoin = root.join(User_.person, JoinType.INNER);
            Join<Person, DocumentType> personDocumentTypeJoin = userPersonJoin.join(Person_.documentType, JoinType.INNER);
            Join<Person, GenderType> personGenderTypeJoin = userPersonJoin.join(Person_.genderType, JoinType.INNER);

            cq.select(cb.construct(
                    UserPatientResponse.class,
                    root.get(User_.userId),
                    root.get(User_.userName),
                    root.get(User_.administrator),
                    root.get(User_.profileImage),
                    userPersonJoin.get(Person_.personName),
                    userPersonJoin.get(Person_.personLastName),
                    personDocumentTypeJoin.get(DocumentType_.documentTypeId),
                    personDocumentTypeJoin.get(DocumentType_.code),
                    userPersonJoin.get(Person_.personDocument),
                    personGenderTypeJoin.get(GenderType_.genderId),
                    personGenderTypeJoin.get(GenderType_.code),
                    userPersonJoin.get(Person_.personPhone),
                    userPersonJoin.get(Person_.personAddress),
                    userPersonJoin.get(Person_.personEmail),
                    userPersonJoin.get(Person_.previousTreatments)
            ));
            cq.distinct(true);

            TypedQuery<UserPatientResponse> query = manager.createQuery(cq);
            List<UserPatientResponse> userPatientResponse = query.getResultList();
            userPatientResponse.forEach(
                    userPatientResponse1 -> userPatientResponse1.addResponseHearingLosses(
                            this.getAllHearingLossesByPersonId(userPatientResponse1.getUserId())
                    )
            );
            result = userPatientResponse;
        } catch (Exception ex) {
            log.error("error en la consulta criteria getAllUserPatients {}", ex.getMessage());
        }
        manager.close();
        return result;
    }

    private List<SpecialityResponseDto> getAllSpecialitiesByDoctorId(UUID doctorId) {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        List<SpecialityResponseDto> result = null;

        try {
            CriteriaQuery<SpecialityResponseDto> cq = cb.createQuery(SpecialityResponseDto.class);

            Root<User> root = cq.from(User.class);
            Join<User, Doctor> userDoctorJoin = root.join(User_.doctor, JoinType.INNER);
            Join<Doctor, Speciality> doctorSpecialityJoin = userDoctorJoin.join(Doctor_.speciality, JoinType.INNER);

            cq.select(
                    cb.construct(
                            SpecialityResponseDto.class,
                            doctorSpecialityJoin.get(Speciality_.specialityId),
                            doctorSpecialityJoin.get(Speciality_.name)
                    )
            );
            cq.distinct(true);

            cq.where(
                    cb.equal(userDoctorJoin.get(Doctor_.doctorId), doctorId)
            );
            TypedQuery<SpecialityResponseDto> query = manager.createQuery(cq);
            result = query.getResultList();
        } catch (Exception ex) {
            log.error("error en la consulta criteria getAllSpecialitiesByDoctorId {}", ex.getMessage());
        }
        manager.close();
        return result;
    }

    private List<HearingLossResponseDto> getAllHearingLossesByPersonId(UUID personId) {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        List<HearingLossResponseDto> result = null;

        try {
            CriteriaQuery<HearingLossResponseDto> cq = cb.createQuery(HearingLossResponseDto.class);

            Root<User> root = cq.from(User.class);
            Join<User, Person> userPersonJoin = root.join(User_.person, JoinType.INNER);
            Join<Person, HearingLoss> personHearingLossJoin = userPersonJoin.join(Person_.hearingLosses, JoinType.INNER);

            cq.select(
                    cb.construct(
                            HearingLossResponseDto.class,
                            personHearingLossJoin.get(HearingLoss_.hearingLossId),
                            personHearingLossJoin.get(HearingLoss_.name)
                    )
            );
            cq.distinct(true);

            cq.where(
                    cb.equal(userPersonJoin.get(Person_.personId), personId)
            );
            TypedQuery<HearingLossResponseDto> query = manager.createQuery(cq);
            result = query.getResultList();
        } catch (Exception ex) {
            log.error("error en la consulta criteria getAllHearingLossesByPersonId {}", ex.getMessage());
        }
        manager.close();
        return result;
    }
}
