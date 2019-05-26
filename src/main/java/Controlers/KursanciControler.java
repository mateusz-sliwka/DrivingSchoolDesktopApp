package Controlers;

import Entities.KursanciEntity;
import Entities.PlatnosciEntity;
import Entities.RezerwacjeEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Array;
import java.sql.Date;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class KursanciControler {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Entities");
    EntityManager entityManager = entityManagerFactory.createEntityManager();


    public List<KursanciEntity> getAll() {
        List<KursanciEntity> All = entityManager.createQuery("SELECT k FROM KursanciEntity k").getResultList();
        return All;
    }

    public boolean add(String imie, String nazwisko, String email, String haslo, String pkk, String pesel) {

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
        List<KursanciEntity> result = (List<KursanciEntity>) entityManager.createQuery("SELECT u FROM KursanciEntity  u WHERE u.email=:email AND u.haslo=:haslo")
                .setParameter("email", email).setParameter("haslo", haslo).getResultList();
        if (result.isEmpty())
            return null;
        return result.get(0);
    }

    public KursanciEntity getByID(long ID) {
        KursanciEntity result = entityManager.find(KursanciEntity.class, ID);
        return result;
    }

    public KursanciEntity getByFS(String napis) {

        String imie = napis.split(" ")[0];
        String nazwisko = napis.split(" ")[1];
        KursanciEntity result = (KursanciEntity) entityManager.createQuery("SELECT i from KursanciEntity i WHERE i.imie=:a AND i.nazwisko=:b")
                .setParameter("a", imie).setParameter("b", nazwisko).getSingleResult();
        return result;

    }

    public void deleteByID(long id) {
        entityManager.getTransaction().begin();
        KursanciEntity ke = entityManager.find(KursanciEntity.class, id);
        entityManager.remove(ke);
        //entityManager.createQuery("DELETE FROM KursanciEntity  r WHERE r.kursantId=:ajdi").setParameter("ajdi",id).executeUpdate();
        entityManager.getTransaction().commit();

    }

    public long getMaxID() {
        long result = (long) entityManager.createQuery("SELECT MAX(ke.kursantId) FROM KursanciEntity ke").getSingleResult();
        return result;
    }

    public void update(KursanciEntity re, long id) {
        entityManager.getTransaction().begin();
        entityManager.createQuery("UPDATE KursanciEntity r SET r.imie=:a, r.nazwisko=:b,r.email=:c,r.haslo=:d,r.pkk=:e,r.pesel=:f where r.kursantId=:g")
                .setParameter("a", re.getImie()).setParameter("b", re.getNazwisko()).setParameter("c", re.getEmail()).setParameter("d", re.getHaslo()).setParameter("e", re.getPkk())
                .setParameter("f", re.getPesel()).setParameter("g", id).executeUpdate();
        entityManager.getTransaction().commit();
    }

    public long getSaldo(KursanciEntity ke) {
        long naleznosc = 0;
        long wplata = 0;
        Collection<RezerwacjeEntity> rezerwacje = ke.getRezerwacjesByKursantId();
        Iterator<RezerwacjeEntity> iterator = rezerwacje.iterator();
        while (iterator.hasNext()) {
            RezerwacjeEntity kes = iterator.next();
            naleznosc += kes.getUslugiByUslugaId().getCena();
            Iterator<PlatnosciEntity> iterator2 = kes.getPlatnoscisByRezerwacjaId().iterator();
            while (iterator2.hasNext())
                wplata += iterator2.next().getKwota();
        }

        return wplata - naleznosc;
    }
}
