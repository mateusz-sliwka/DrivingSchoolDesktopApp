package Controlers;

import Entities.InstruktorzyEntity;
import Entities.KursanciEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class KursanciControler {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Entities");
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    public List<KursanciEntity> getAll() {
        List<KursanciEntity> All = entityManager.createQuery("SELECT k FROM KursanciEntity k").getResultList();
        return All;
    }

    public KursanciEntity login(String email, String haslo) {
        List<KursanciEntity> result = entityManager.createQuery("SELECT u FROM KursanciEntity  u WHERE u.email=:email AND u.haslo=:haslo")
                .setParameter("email",email).setParameter("haslo",haslo).getResultList();
        if(result.isEmpty())
            return null;
        return result.get(0);
    }
}
