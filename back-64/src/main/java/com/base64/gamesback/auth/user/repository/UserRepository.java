package com.base64.gamesback.auth.user.repository;

import com.base64.gamesback.auth.user.dto.projection.CountUsersAndTest;
import com.base64.gamesback.auth.user.dto.projection.UserDoctorData;
import com.base64.gamesback.auth.user.dto.projection.UserPersonData;
import com.base64.gamesback.auth.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository  extends JpaRepository<User, UUID>, UserRepositoryCustom {

    User getUserByUserName(String userName);

    Boolean existsUserByUserName(String userName);

    @Query(value = "SELECT u.user_name AS userName, u.administrator AS administrator, u.profile_image AS profileImage, " +
            "p.name AS personName, p.last_name AS personLastName, " +
            "p.document AS personDocument, p.address AS personAddress, p.phone AS personPhone, " +
            "p.email AS personEmail FROM main.user u INNER JOIN main.person p ON u.user_id = p.user_id WHERE u.user_id = :userId", nativeQuery = true)
    UserPersonData getUserPatientId(@Param("userId") UUID userId);

    @Query(value = "SELECT u.user_id AS userId, u.user_name AS userName, u.administrator AS administrator, u.profile_image AS profileImage, " +
            "p.name AS personName, p.last_name AS personLastName, " +
            "p.document AS personDocument, p.address AS personAddress, p.phone AS personPhone, " +
            "p.email AS personEmail FROM main.user u INNER JOIN main.person p ON u.user_id = p.user_id ", nativeQuery = true)
    List<UserPersonData> getAllUsersPatient();

    @Query(value = "SELECT u.user_name AS userName, u.administrator AS administrator, u.profile_image AS profileImage, " +
            "d.name AS doctorName, d.last_name AS doctorLastName, " +
            "d.image AS doctorImage, d.address AS doctorAddress, d.phone AS doctorPhone, " +
            "d.email AS doctorEmail, s.speciality_id AS speciality_id, s.name AS speciality_name " +
            "FROM main.user u INNER JOIN main.doctor d ON u.user_id = d.user_id " +
            "inner join main.doctor_speciality ds on d.user_id = ds.doctor_id " +
            "inner join main.speciality s on ds.speciality_id = s.speciality_id " +
            "WHERE u.user_id = :userId", nativeQuery = true)
    UserDoctorData getUserDoctorId(@Param("userId") UUID userId);

    @Query(value = "SELECT u.user_id AS userId, u.user_name AS userName, u.administrator AS administrator, u.profile_image AS profileImage, " +
            "d.name AS doctorName, d.last_name AS doctorLastName, " +
            "d.image AS doctorImage, d.address AS doctorAddress, d.phone AS doctorPhone, " +
            "d.email AS doctorEmail s.speciality_id as specialityId, s.name as specialityName FROM main.user u INNER JOIN main.doctor d ON u.user_id = d.user_id " +
            "inner join main.doctor_speciality ds on d.user_id = ds.user_id " +
            "inner join main.speciality s on ds.speciality_id = s.speciality ", nativeQuery = true)
    List<UserDoctorData> getAllUsersDoctor();

    @Query(value = "SELECT count(d.user_id) AS userDoctor, count(p.user_id) AS userPerson, (select count(t.test_id) from main.test t) as test " +
            " FROM main.user u LEFT JOIN main.doctor d ON u.user_id = d.user_id " +
            "left join main.person p on u.user_id = p.user_id ", nativeQuery = true)
    CountUsersAndTest getCountUsersAndTest();

    User getUserByUserNameAndPersonPersonEmail(String userName, String email);

    Optional<User> findByUserIdAndUserName(UUID userId, String userName);
}
