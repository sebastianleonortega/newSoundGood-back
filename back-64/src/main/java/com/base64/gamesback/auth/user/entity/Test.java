package com.base64.gamesback.auth.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Entity
@Table(name = "test", schema = "main")
@NoArgsConstructor
public class Test {

    @Id
    @GeneratedValue
    private UUID testId;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @Column(name = "test_type", nullable = false, length = 100)
    private String testType;

    @Column(name = "test_date", nullable = false)
    @CreationTimestamp
    private LocalDateTime testDate;

    @Column(name = "result_left")
    private String resultLeft;

    @Column(name = "result_right")
    private String resultRight;

    @Column(name = "result_numeric")
    private String resultNumeric;

    public Test(Person person, String testType, String resultLeft, String resultRight, String resultNumeric) {
        this.person = person;
        this.testType = testType;
        this.resultLeft = resultLeft;
        this.resultRight = resultRight;
        this.resultNumeric = resultNumeric;
    }

    public static Test create(Person person, String testType, String resultLeft, String resultRight, String resultNumeric) {
        return new Test(person, testType, resultLeft, resultRight, resultNumeric);
    }
}

