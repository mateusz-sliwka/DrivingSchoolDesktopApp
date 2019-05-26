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

    public UslugiEntity getByID(long ID) {
        UslugiEntity result = entityManager.find(UslugiEntity.class, ID);
        return result;
    }

    public List<UslugiEntity> getAll() {
        List<UslugiEntity> All = entityManager.createQuery("SELECT k FROM UslugiEntity k").getResultList();
        return All;
    }

    public UslugiEntity getByName(String napis) {
        UslugiEntity result = (UslugiEntity) entityManager.createQuery("SELECT u FROM UslugiEntity  u WHERE u.nazwa=:a").setParameter("a", napis).getSingleResult();
        return result;
    }

    public void add(UslugiEntity ue) {
        entityManager.getTransaction().begin();
        ;
        entityManager.persist(ue);
        entityManager.getTransaction().commit();
    }

    public void deleteByID(long ID) {
        entityManager.getTransaction().begin();
        UslugiEntity ke = entityManager.find(UslugiEntity.class, ID);
        entityManager.remove(ke);
        //entityManager.createQuery("DELETE FROM KursanciEntity  r WHERE r.kursantId=:ajdi").setParameter("ajdi",id).executeUpdate();
        entityManager.getTransaction().commit();

    }

    public void update(UslugiEntity re, long id) {
        entityManager.getTransaction().begin();
        entityManager.createQuery("UPDATE UslugiEntity r SET r.nazwa=:a, r.cena=:b where r.uslugaId=:c")
                .setParameter("a", re.getNazwa()).setParameter("b", re.getCena()).setParameter("c", id).executeUpdate();
        entityManager.getTransaction().commit();
    }

}
