package com.base64.gamesback.auth.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.print.Doc;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "user", schema = "main")
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private UUID userId;

    @OneToOne(mappedBy = "user")
    private Person person;

    @Column(name = "user_name", unique = true)
    private String userName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "locked",nullable = false)
    private boolean locked;

    @Column(name = "login_attempts")
    private int loginAttempts;

    @Column(name = "login_attempts_mfa")
    private int loginAttemptsMfa;

    @Column(name = "administrator")
    private boolean administrator;

    @Column(name = "profile_image", columnDefinition = "TEXT")
    private String profileImage;

    @OneToOne(mappedBy = "user")
    private Doctor doctor;

    @Column(name = "have_password_by_admin")
    private Boolean havePasswordByAdmin;

    @Column(name = "code_verification")
    private String codeVerification;

    @Column(name = "created_code_verification")
    private LocalDateTime createCodeVerification;

    @Column(name = "quantity_resent_email")
    private int quantityResentEmail;

    @Column(name = "reset_password_updateAt")
    private LocalDateTime resetPasswordUpdateAt;

    public User(String userName, String password, String profileImage, Boolean havePasswordByAdmin) {
        this.userName = userName;
        this.password = password;
        this.administrator = false;
        this.profileImage = profileImage;
        this.havePasswordByAdmin = havePasswordByAdmin;
    }

    public static User create( String userName, String password, String profileImag, Boolean isCreatedByAdmin){
        return new User(userName, password, profileImag, isCreatedByAdmin);
    }

    public void update(String userName, String profileImage){
        this.userName = userName;
        this.profileImage = profileImage;
    }

    public void addCodeVerification(String codeVerification) {
        this.codeVerification = codeVerification;
        this.createCodeVerification = LocalDateTime.now();
    }

    public void resetPassword(String password, LocalDateTime resetPasswordUpdateAt, Integer loginAttempts, Integer loginAttemptsMfa) {
        this.password = password;
        this.resetPasswordUpdateAt = resetPasswordUpdateAt;
        this.loginAttempts = loginAttempts;
        this.loginAttemptsMfa = loginAttemptsMfa;
    }

    public void resetCodeVerification(){
        this.codeVerification = null;
    }

    public void updateLoginAttempts(int attempts){
        this.loginAttempts = attempts;
    }

    public void updateLocked(boolean locked){
        this.locked = locked;
    }

    public void updateLoginAttemptsMfa(int loginAttemptsMfa){
        this.loginAttemptsMfa = loginAttemptsMfa;
    }

    public void updateQuantityResentEmail(int quantityResentEmail){
        this.quantityResentEmail = quantityResentEmail;
    }

    public void updateResetPasswordUpdateAt(LocalDateTime resetPasswordUpdateAt){
        this.resetPasswordUpdateAt = resetPasswordUpdateAt;
    }

    public void updatePassword(String password){
        this.password = password;
    }

    public void updateHavePasswordByAdmin(){
        this.havePasswordByAdmin = false;
    }
}