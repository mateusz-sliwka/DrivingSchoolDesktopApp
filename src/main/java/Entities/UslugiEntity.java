package Entities;

import javax.persistence.*;

@Entity
@Table(name = "USLUGI", schema = "SZKOLAJAZDY", catalog = "")
public class UslugiEntity {
    private long uslugaId;
    private String nazwa;
    private Long cena;

    @Id
    @Column(name = "USLUGA_ID")
    public long getUslugaId() {
        return uslugaId;
    }

    public void setUslugaId(long uslugaId) {
        this.uslugaId = uslugaId;
    }

    @Basic
    @Column(name = "NAZWA")
    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    @Basic
    @Column(name = "CENA")
    public Long getCena() {
        return cena;
    }

    public void setCena(Long cena) {
        this.cena = cena;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UslugiEntity that = (UslugiEntity) o;
        if (uslugaId != that.uslugaId) return false;
        if (nazwa != null ? !nazwa.equals(that.nazwa) : that.nazwa != null) return false;
        if (cena != null ? !cena.equals(that.cena) : that.cena != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (uslugaId ^ (uslugaId >>> 32));
        result = 31 * result + (nazwa != null ? nazwa.hashCode() : 0);
        result = 31 * result + (cena != null ? cena.hashCode() : 0);
        return result;
    }
}
