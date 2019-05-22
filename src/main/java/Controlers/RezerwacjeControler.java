package Controlers;

import Entities.InstruktorzyEntity;
import Entities.UslugiEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class RezerwacjeControler {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Entities");
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    public List getAll() {
        List All = entityManager.createQuery("SELECT r FROM RezerwacjeEntity r").getResultList();
        return All;
    }

    public List getByInstruktor(long id){
        List byInstruktor = entityManager.createQuery("SELECT r FROM RezerwacjeEntity  r WHERE r.instruktorId=:instruktor").setParameter("instruktor",id).getResultList();
        return byInstruktor;
    }
    public List getByUsluga(long id){
        List byUsluga = entityManager.createQuery("SELECT r FROM RezerwacjeEntity  r WHERE r.uslugaId=:instruktor").setParameter("instruktor",id).getResultList();
        return byUsluga;
    }
    public List getByKursant(long id){
        List byUsluga = entityManager.createQuery("SELECT r FROM RezerwacjeEntity  r WHERE r.kursantId=:instruktor").setParameter("instruktor",id).getResultList();
        return byUsluga;
    }
}