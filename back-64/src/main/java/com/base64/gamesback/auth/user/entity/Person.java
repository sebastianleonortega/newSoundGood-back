package com.base64.gamesback.auth.user.entity;

import com.base64.gamesback.documentType.entity.DocumentType;
import com.base64.gamesback.hearing_loss.entity.HearingLoss;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Getter
@Entity
@Table(name = "person", schema = "main")
@NoArgsConstructor
public class Person {

    @Id
    private UUID personId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "name", nullable = false, length = 100)
    private String personName;

    @Column(name = "last_name", nullable = false, length = 100)
    private String personLastName;

    @Column(name = "document", nullable = false, length = 15, unique = true)
    private String personDocument;

    @Column(name = "address", nullable = false, length = 50)
    private String personAddress;

    @Column(name = "phone", nullable = false, length = 13)
    private String personPhone;

    @Column(name = "email", nullable = false, length = 200, unique = true)
    private String personEmail;

    @Column(name = "type_of_hearing_loss", length = 200)
    private String typeOfHearingLoss;

    @Column(name = "previous_treatments", length = 200)
    private String previousTreatments;

    @ManyToMany(cascade = CascadeType.REFRESH)
    @JoinTable(
            name = "person_hearing_loss",
            schema = "main",
            joinColumns = @JoinColumn(name = "person_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "hearing_loss_id", nullable = false),
            uniqueConstraints = @UniqueConstraint(columnNames = {"person_id", "hearing_loss_id"}, name = "uc_person_hearing_loss")
    )
    private Set<HearingLoss> hearingLosses;

    @ManyToOne
    @JoinColumn(name = "document_type_id", nullable = false)
    private DocumentType documentType;

//    @ManyToOne
//    @JoinColumn(name = "gender_type_id", nullable = false)
//    private GenderType genderType;

    public Person(String personName, String personLastName, String personDocument, String personAddress, String personPhone, String personEmail, String typeOfHearingLoss, String previousTreatments) {
        this.personName = personName;
        this.personLastName = personLastName;
        this.personDocument = personDocument;
        this.personAddress = personAddress;
        this.personPhone = personPhone;
        this.personEmail = personEmail;
        this.typeOfHearingLoss = typeOfHearingLoss;
        this.previousTreatments = previousTreatments;
    }

    public static Person create(String personName, String personLastName, String personDocument, String personAddress, String personPhone, String personEmail, String typeOfHearingLoss, String previousTreatments){
        return  new Person(personName, personLastName, personDocument, personAddress, personPhone, personEmail, typeOfHearingLoss, previousTreatments);
    }

    public void update(String personName, String personLastName, String personDocument, String personAddress, String personPhone, String personEmail, String typeOfHearingLoss, String previousTreatments){
        this.personName = personName;
        this.personLastName = personLastName;
        this.personDocument = personDocument;
        this.personAddress = personAddress;
        this.personPhone = personPhone;
        this.personEmail = personEmail;
        this.typeOfHearingLoss = typeOfHearingLoss;
        this.previousTreatments = previousTreatments;
    }

    public void  addUser(User user){
        this.user = user;
    }

    public void addHearingLoss(Set<HearingLoss> hearingLosses){
        this.hearingLosses = hearingLosses;
    }

    public void addDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

//    public void addGenderType(GenderType genderType) {
//        this.genderType = genderType;
//
//    }
}
