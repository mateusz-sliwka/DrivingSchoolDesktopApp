package Controlers;

import Entities.PlatnosciEntity;
import Entities.UslugiEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class PlatnosciControler {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Entities");
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    public PlatnosciEntity getByID(long ID){
        PlatnosciEntity result = entityManager.find(PlatnosciEntity.class,ID);
        return result;
    }
    public List<PlatnosciEntity> getAll() {
        List<PlatnosciEntity> All = entityManager.createQuery("SELECT k FROM PlatnosciEntity k").getResultList();
        return All;
    }

}
