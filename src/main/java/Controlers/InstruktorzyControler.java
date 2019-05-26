package Controlers;

import Entities.InstruktorzyEntity;
import Entities.RezerwacjeEntity;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Date;
import java.util.List;

public class InstruktorzyControler {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Entities");
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    public List getAll(){
        List all = entityManager.createQuery("SELECT c FROM InstruktorzyEntity  c").getResultList();
        return all;
    }

   public List getByTime (String a, String b){
        List byTime = entityManager.createQuery("SELECT i FROM InstruktorzyEntity  i WHERE i.godzRozpoczecia>=:a AND i.godzZakonczenia<=:b").
                setParameter("a", a).setParameter("b",b).getResultList();
        return byTime;
    }
    public InstruktorzyEntity getByID(long ID){
        InstruktorzyEntity result = (InstruktorzyEntity) entityManager.createQuery("SELECT i FROM InstruktorzyEntity  i WHERE i.instruktorId=:a").setParameter("a",ID).getSingleResult();
        return result;
    }
    public InstruktorzyEntity login(String email, String haslo) {
        List<InstruktorzyEntity> result = entityManager.createQuery("SELECT u FROM InstruktorzyEntity  u WHERE u.email=:email AND u.haslo=:haslo")
                .setParameter("email",email).setParameter("haslo",haslo).getResultList();
        if(result.isEmpty())
            return null;
        return result.get(0);
    }

    public InstruktorzyEntity loginAdmin(String email, String haslo) {
        List<InstruktorzyEntity> result = entityManager.createQuery("SELECT u FROM InstruktorzyEntity  u WHERE u.email=:email AND u.haslo=:haslo AND u.czyAdmin=1")
                .setParameter("email",email).setParameter("haslo",haslo).getResultList();
        if(result.isEmpty())
            return null;
        return result.get(0);
    }
    public InstruktorzyEntity getByFS(String napis){
        String imie = napis.split(" ")[0];
        String nazwisko = napis.split(" ")[1];
        InstruktorzyEntity result = (InstruktorzyEntity) entityManager.createQuery("SELECT i from InstruktorzyEntity i WHERE i.imie=:a AND i.nazwisko=:b")
                .setParameter("a",imie).setParameter("b",nazwisko).getSingleResult();
        return result;
    }
}
