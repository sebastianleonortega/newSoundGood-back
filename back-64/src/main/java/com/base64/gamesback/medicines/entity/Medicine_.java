package com.base64.gamesback.medicines.entity;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

import javax.annotation.processing.Generated;
import java.util.UUID;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Medicine.class)
public abstract class Medicine_ {

        public static volatile SingularAttribute<Medicine, UUID> medicineId;
        public static volatile SingularAttribute<Medicine, String> name;

}
