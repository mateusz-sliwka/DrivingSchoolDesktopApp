package Controlers;

import Entities.InstruktorzyEntity;
import Entities.RezerwacjeEntity;
import Entities.UslugiEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Date;
import java.util.List;

public class RezerwacjeControler {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Entities");
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    public List getAll() {
        List<RezerwacjeEntity> All = entityManager.createQuery("SELECT r FROM RezerwacjeEntity r").getResultList();
        return All;
    }

    public List getByInstruktor(long id) {
        List<RezerwacjeEntity> byInstruktor = entityManager.createQuery("SELECT r FROM RezerwacjeEntity  r WHERE r.instruktorId=:instruktor").setParameter("instruktor", id).getResultList();
        return byInstruktor;
    }

    public List getByUsluga(long id) {
        List byUsluga = entityManager.createQuery("SELECT r FROM RezerwacjeEntity  r WHERE r.uslugaId=:instruktor").setParameter("instruktor", id).getResultList();
        return byUsluga;
    }

    public List getByKursant(long id) {
        List byUsluga = entityManager.createQuery("SELECT r FROM RezerwacjeEntity  r WHERE r.kursantId=:instruktor").setParameter("instruktor", id).getResultList();
        return byUsluga;
    }

    public void deleteByID(long id) {
        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE FROM RezerwacjeEntity  r WHERE r.rezerwacjaId=:ajdi").setParameter("ajdi", id).executeUpdate();
        entityManager.getTransaction().commit();
    }

    public RezerwacjeEntity getByID(long id) {
        RezerwacjeEntity re = entityManager.find(RezerwacjeEntity.class, id);
        return re;
    }

    public void add(RezerwacjeEntity re) {
        entityManager.getTransaction().begin();
        entityManager.persist(re);
        entityManager.getTransaction().commit();
    }

    public void update(RezerwacjeEntity re, long id) {
        entityManager.getTransaction().begin();
        entityManager.createQuery("UPDATE RezerwacjeEntity r SET r.uslugaId=:a, r.kursantId=:b,r.instruktorId=:c,r.dataRezerwacji=:d,r.kategoriaId=:e, r.godzRozpoczecia=:f where r.rezerwacjaId=:g")
                .setParameter("a", re.getUslugaId()).setParameter("b", re.getKursantId()).setParameter("c", re.getInstruktorId()).setParameter("d", re.getDataRezerwacji())
                .setParameter("e",re.getKategoriaId()).setParameter("f",re.getGodzRozpoczecia()).setParameter("g",id).executeUpdate();
        entityManager.getTransaction().commit();
    }
}
    
