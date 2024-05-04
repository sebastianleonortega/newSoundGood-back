package com.base64.gamesback.hearing_loss.entity;

import com.base64.gamesback.auth.user.entity.Person;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "hearing_loss", schema = "main")
@Getter
@NoArgsConstructor
public class HearingLoss {

    @Id
    @GeneratedValue
    @Column(name = "hearing_loss_id")
    private UUID hearingLossId;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "hearingLosses")
    private List<Person> person;

    public HearingLoss(String name) {
        this.name = name;
    }

    public void updateHearingLoss(String name) {
        this.name = name;
    }

    public static HearingLoss create(String name) {
        return new HearingLoss(
                name
        );
    }

}
