package Controlers;

import Entities.KategorieEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.Serializable;
import java.util.List;

public class KategorieControler implements Serializable {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Entities");
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    public List<KategorieEntity> getAll() {
        List<KategorieEntity> result = entityManager.createQuery("SELECT k FROM KategorieEntity k").getResultList();
        return result;
    }

    public long getIDbyname(String name) {
        long id = (long) entityManager.createQuery("select ke.kategoriaId from KategorieEntity ke where ke.symbol=:a")
                .setParameter("a", name).getSingleResult();
        return id;

    }

    public KategorieEntity getByID(long id) {
        KategorieEntity result = entityManager.find(KategorieEntity.class, id);
        return result;
    }
}
