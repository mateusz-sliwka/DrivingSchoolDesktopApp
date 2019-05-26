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

    public PlatnosciEntity getByID(long ID) {
        PlatnosciEntity result = entityManager.find(PlatnosciEntity.class, ID);
        return result;
    }

    public List<PlatnosciEntity> getAll() {
        List<PlatnosciEntity> All = entityManager.createQuery("SELECT k FROM PlatnosciEntity k").getResultList();
        return All;
    }

    public void add(PlatnosciEntity pe) {
        entityManager.getTransaction().begin();
        entityManager.persist(pe);
        entityManager.getTransaction().commit();
    }

    public void delete(long id) {
        entityManager.getTransaction().begin();
        PlatnosciEntity pe = entityManager.find(PlatnosciEntity.class, id);
        entityManager.remove(pe);
        entityManager.getTransaction().commit();

    }
    public void update(long id, PlatnosciEntity pe){
        entityManager.getTransaction().begin();
        entityManager.createQuery("UPDATE PlatnosciEntity p SET p.kursantId=:a, p.kwota=:b").setParameter("a",pe.getKursantId()).setParameter("b",pe.getKwota()).executeUpdate();
        entityManager.getTransaction().commit();
    }

}
