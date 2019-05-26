package Controlers;

import Entities.KursanciEntity;
import Entities.UslugiEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class UslugiControler {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Entities");
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    public UslugiEntity getByID(long ID){
        UslugiEntity result = (UslugiEntity) entityManager.createQuery("SELECT u FROM UslugiEntity u WHERE u.uslugaId=:a").setParameter("a",ID).getSingleResult();
        return result;
    }
    public List<UslugiEntity> getAll() {
        List<UslugiEntity> All = entityManager.createQuery("SELECT k FROM UslugiEntity k").getResultList();
        return All;
    }
    public UslugiEntity getByName(String napis){
        UslugiEntity result = (UslugiEntity)entityManager.createQuery("SELECT u FROM UslugiEntity  u WHERE u.nazwa=:a").setParameter("a",napis).getSingleResult();
        return result;
    }
}
