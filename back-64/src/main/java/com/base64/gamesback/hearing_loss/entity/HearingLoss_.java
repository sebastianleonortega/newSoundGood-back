package com.base64.gamesback.hearing_loss.entity;

import com.base64.gamesback.auth.user.entity.Person;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

import javax.annotation.processing.Generated;
import java.util.UUID;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(HearingLoss.class)
public abstract class HearingLoss_ {

        public static volatile SingularAttribute<HearingLoss, UUID> hearingLossId;
        public static volatile SingularAttribute<HearingLoss, String> name;
        public static volatile ListAttribute<HearingLoss, Person> person;

}
