package Controlers;

import Entities.KategorieInstruktorowEntity;
import Entities.UslugiEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.Serializable;
import java.util.List;

public class KategorieInstruktorowControler implements Serializable {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Entities");
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    public void add(KategorieInstruktorowEntity ki) {
        entityManager.getTransaction().begin();
        entityManager.persist(ki);
        entityManager.getTransaction().commit();
    }

    public KategorieInstruktorowControler getByID(long ID) {
        KategorieInstruktorowControler result = entityManager.find(KategorieInstruktorowControler.class, ID);
        return result;
    }

    public List<KategorieInstruktorowEntity> getAll() {
        List<KategorieInstruktorowEntity> All = entityManager.createQuery("SELECT k FROM KategorieInstruktorowEntity k").getResultList();
        return All;
    }
    public void deleteByID(long ID){
        KategorieInstruktorowEntity kic = entityManager.find(KategorieInstruktorowEntity.class,ID);
        entityManager.getTransaction().begin();
        entityManager.remove(kic);
        entityManager.getTransaction().commit();
    }
}
