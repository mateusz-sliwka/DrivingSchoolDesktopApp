package Controlers;

import Entities.KategorieInstruktorowEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.Serializable;

public class KategorieInstruktorowControler implements Serializable {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Entities");
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    public void add(KategorieInstruktorowEntity ki) {
        entityManager.getTransaction().begin();
        entityManager.persist(ki);
        entityManager.getTransaction().commit();
    }

}
