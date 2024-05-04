package com.base64.gamesback.auth.user.repository;

import com.base64.gamesback.auth.user.dto.projection.userDoctorData;
import com.base64.gamesback.auth.user.dto.projection.userPersonData;
import com.base64.gamesback.auth.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface UserRepository  extends JpaRepository<User, UUID> {

    User getUserByUserName(String userName);

    Boolean existsUserByUserName(String userName);

    @Query(value = "SELECT u.user_name AS userName, u.administrator AS administrator, u.profile_image AS profileImage, " +
            "p.name AS personName, p.last_name AS personLastName, " +
            "p.document AS personDocument, p.address AS personAddress, p.phone AS personPhone, " +
            "p.email AS personEmail FROM main.user u INNER JOIN main.person p ON u.user_id = p.user_id WHERE u.user_id = :userId", nativeQuery = true)
    userPersonData getUserPatientId(@Param("userId") UUID userId);

    @Query(value = "SELECT u.user_id AS userId, u.user_name AS userName, u.administrator AS administrator, u.profile_image AS profileImage, " +
            "p.name AS personName, p.last_name AS personLastName, " +
            "p.document AS personDocument, p.address AS personAddress, p.phone AS personPhone, " +
            "p.email AS personEmail FROM main.user u INNER JOIN main.person p ON u.user_id = p.user_id ", nativeQuery = true)
    List<userPersonData> getAllUsersPatient();

    @Query(value = "SELECT u.user_name AS userName, u.administrator AS administrator, u.profile_image AS profileImage, " +
            "d.name AS doctorName, d.last_name AS doctorLastName, " +
            "d.image AS doctorImage, d.address AS doctorAddress, d.phone AS doctorPhone, " +
            "d.email AS docotorEmail FROM main.user u INNER JOIN main.doctor d ON u.user_id = d.user_id WHERE u.user_id = :userId", nativeQuery = true)
    userDoctorData getUserDoctorId(@Param("userId") UUID userId);

    @Query(value = "SELECT u.user_id AS userId, u.user_name AS userName, u.administrator AS administrator, u.profile_image AS profileImage, " +
            "d.name AS doctorName, d.last_name AS doctorLastName, " +
            "d.image AS doctorImage, d.address AS doctorAddress, d.phone AS doctorPhone, " +
            "d.email AS docotorEmail FROM main.user u INNER JOIN main.doctor d ON u.user_id = d.user_id ", nativeQuery = true)
    List<userDoctorData> getAllUsersDoctor();
}
