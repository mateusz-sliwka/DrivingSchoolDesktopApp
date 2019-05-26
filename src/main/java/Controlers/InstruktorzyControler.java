package Controlers;

import Entities.InstruktorzyEntity;
import Entities.KursanciEntity;
import Entities.RezerwacjeEntity;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class InstruktorzyControler implements Serializable {
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
        InstruktorzyEntity result = entityManager.find(InstruktorzyEntity.class,ID);
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
    public void update(InstruktorzyEntity re, long id){
        entityManager.getTransaction().begin();
        entityManager.createQuery("UPDATE InstruktorzyEntity r SET r.imie=:a, r.nazwisko=:b,r.email=:c,r.haslo=:d where r.instruktorId=:e")
                .setParameter("a",re.getImie()).setParameter("b",re.getNazwisko()).setParameter("c",re.getEmail()).setParameter("d",re.getHaslo()).setParameter("e",id).executeUpdate();
        entityManager.getTransaction().commit();
    }
    public void deleteByID(long id) {
        entityManager.getTransaction().begin();
        InstruktorzyEntity ke = entityManager.find(InstruktorzyEntity.class, id);
        entityManager.remove(ke);
        entityManager.getTransaction().commit();
    }
    public void add(InstruktorzyEntity ie){
        entityManager.getTransaction().begin();
        entityManager.persist(ie);
        entityManager.getTransaction().commit();
    }
    }
