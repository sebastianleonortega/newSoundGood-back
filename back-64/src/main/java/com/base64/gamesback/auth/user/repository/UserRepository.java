package com.base64.gamesback.auth.user.repository;

import com.base64.gamesback.auth.user.dto.projection.userData;
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
            "p.person_name AS personName, p.person_last_name AS personLastName, " +
            "p.person_document AS personDocument, p.person_address AS personAddress, p.person_phone AS personPhone, " +
            "p.person_email AS personEmail FROM main.user u INNER JOIN main.person p ON u.user_id = p.user_id WHERE u.user_id = :userId", nativeQuery = true)
    userData getUserId(@Param("userId") UUID userId);

    @Query(value = "SELECT u.user_name AS userName, u.administrator AS administrator, u.profile_image AS profileImage, " +
            "p.person_name AS personName, p.person_last_name AS personLastName, " +
            "p.person_document AS personDocument, p.person_address AS personAddress, p.person_phone AS personPhone, " +
            "p.person_email AS personEmail FROM main.user u INNER JOIN main.person p ON u.user_id = p.user_id ", nativeQuery = true)
    List<userData> getAllUsers();
}
