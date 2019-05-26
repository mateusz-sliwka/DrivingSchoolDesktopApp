package Controlers;

import Entities.InstruktorzyEntity;
import Entities.KursanciEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Date;
import java.util.List;

public class KursanciControler {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Entities");
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    public List<KursanciEntity> getAll() {
        List<KursanciEntity> All = entityManager.createQuery("SELECT k FROM KursanciEntity k").getResultList();
        return All;
    }
    public boolean add(String imie, String nazwisko, String email, String haslo, String pkk, String pesel){
        entityManager.getTransaction().begin();
        KursanciEntity kursant = new KursanciEntity();
        kursant.setImie(imie);
        kursant.setNazwisko(nazwisko);
        kursant.setPesel(pesel);
        kursant.setEmail(email);
        kursant.setPkk(pkk);
        kursant.setHaslo(haslo);
        kursant.setDataRejestracji(new Date(System.currentTimeMillis()));
        entityManager.persist(kursant);
        entityManager.getTransaction().commit();
        return true;
    }
    public KursanciEntity login(String email, String haslo) {
        List<KursanciEntity> result = entityManager.createQuery("SELECT u FROM KursanciEntity  u WHERE u.email=:email AND u.haslo=:haslo")
                .setParameter("email",email).setParameter("haslo",haslo).getResultList();
        if(result.isEmpty())
            return null;
        return result.get(0);
    }
    public KursanciEntity getByID(long ID){
            KursanciEntity result = (KursanciEntity) entityManager.createQuery("SELECT i FROM KursanciEntity  i WHERE i.kursantId=:a").setParameter("a",ID).getSingleResult();
            return result;
    }
    public KursanciEntity getByFS(String napis){

            String imie = napis.split(" ")[0];
            String nazwisko = napis.split(" ")[1];
            KursanciEntity result = (KursanciEntity) entityManager.createQuery("SELECT i from KursanciEntity i WHERE i.imie=:a AND i.nazwisko=:b")
                    .setParameter("a",imie).setParameter("b",nazwisko).getSingleResult();
            return result;

    }
}
