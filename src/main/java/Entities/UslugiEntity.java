package Entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "USLUGI", schema = "SZKOLAJAZDY", catalog = "")
public class UslugiEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USLUGA_ID")
    private long uslugaId;
    private String nazwa;
    private long cena;
    private Collection<RezerwacjeEntity> rezerwacjesByUslugaId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USLUGA_ID", nullable = false, precision = 0)
    public long getUslugaId() {
        return uslugaId;
    }
    public void setUslugaId(long uslugaId) {
        this.uslugaId = uslugaId;
    }

    @Basic
    @Column(name = "NAZWA", nullable = false, length = 20)
    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    @Basic
    @Column(name = "CENA", nullable = true, precision = 0)
    public long getCena() {
        return cena;
    }

    public void setCena(Long cena) {
        this.cena = cena;
    }

    public void setCena(long cena) {
        this.cena = cena;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UslugiEntity that = (UslugiEntity) o;
        if (uslugaId != that.uslugaId) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (uslugaId ^ (uslugaId >>> 32));
        result = 31 * result + (nazwa != null ? nazwa.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "uslugiByUslugaId", orphanRemoval = true)
    public Collection<RezerwacjeEntity> getRezerwacjesByUslugaId() {
        return rezerwacjesByUslugaId;
    }

    public void setRezerwacjesByUslugaId(Collection<RezerwacjeEntity> rezerwacjesByUslugaId) {
        this.rezerwacjesByUslugaId = rezerwacjesByUslugaId;
    }
}
