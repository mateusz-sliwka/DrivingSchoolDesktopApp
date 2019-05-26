package Entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "INSTRUKTORZY", schema = "SZKOLAJAZDY", catalog = "")
public class InstruktorzyEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long instruktorId;
    private String imie;
    private String nazwisko;
    private String godzRozpoczecia;
    private String godzZakonczenia;
    private Date dataDodania;
    private String email;
    private String haslo;
    private long czyAdmin;
    private Collection<KategorieInstruktorowEntity> kategorieInstruktorowsByInstruktorId;
    private Collection<RezerwacjeEntity> rezerwacjesByInstruktorId;

    @Id
    @Column(name = "INSTRUKTOR_ID", nullable = false, precision = 0)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getInstruktorId() {
        return instruktorId;
    }

    public void setInstruktorId(long instruktorId) {
        this.instruktorId = instruktorId;
    }

    @Basic
    @Column(name = "IMIE", nullable = false, length = 20)
    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    @Basic
    @Column(name = "NAZWISKO", nullable = false, length = 20)
    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    @Basic
    @Column(name = "GODZ_ROZPOCZECIA", nullable = false, length = 20)
    public String getGodzRozpoczecia() {
        return godzRozpoczecia;
    }

    public void setGodzRozpoczecia(String godzRozpoczecia) {
        this.godzRozpoczecia = godzRozpoczecia;
    }

    @Basic
    @Column(name = "GODZ_ZAKONCZENIA", nullable = true, length = 20)
    public String getGodzZakonczenia() {
        return godzZakonczenia;
    }

    public void setGodzZakonczenia(String godzZakonczenia) {
        this.godzZakonczenia = godzZakonczenia;
    }

    @Basic
    @Column(name = "DATA_DODANIA", nullable = true)
    public Date getDataDodania() {
        return dataDodania;
    }


    public void setDataDodania(Date dataDodania) {
        this.dataDodania = dataDodania;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InstruktorzyEntity that = (InstruktorzyEntity) o;
        if (instruktorId != that.instruktorId) return false;
        if (imie != null ? !imie.equals(that.imie) : that.imie != null) return false;
        if (nazwisko != null ? !nazwisko.equals(that.nazwisko) : that.nazwisko != null) return false;
        if (godzRozpoczecia != null ? !godzRozpoczecia.equals(that.godzRozpoczecia) : that.godzRozpoczecia != null)
            return false;
        if (godzZakonczenia != null ? !godzZakonczenia.equals(that.godzZakonczenia) : that.godzZakonczenia != null)
            return false;
        if (dataDodania != null ? !dataDodania.equals(that.dataDodania) : that.dataDodania != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (instruktorId ^ (instruktorId >>> 32));
        result = 31 * result + (imie != null ? imie.hashCode() : 0);
        result = 31 * result + (nazwisko != null ? nazwisko.hashCode() : 0);
        result = 31 * result + (godzRozpoczecia != null ? godzRozpoczecia.hashCode() : 0);
        result = 31 * result + (godzZakonczenia != null ? godzZakonczenia.hashCode() : 0);
        result = 31 * result + (dataDodania != null ? dataDodania.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "EMAIL", nullable = false, length = 20)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "HASLO", nullable = false, length = 20)
    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    @Basic
    @Column(name = "CZY_ADMIN", nullable = false, precision = 0)
    public long getCzyAdmin() {
        return czyAdmin;
    }

    public void setCzyAdmin(long czyAdmin) {
        this.czyAdmin = czyAdmin;
    }

    @Transient
    public String getImieNazwisko() {
        return this.imie + " " + this.nazwisko;
    }

    @OneToMany(mappedBy = "instruktorzyByKategoriaId", orphanRemoval = true)
    public Collection<KategorieInstruktorowEntity> getKategorieInstruktorowsByInstruktorId() {
        return kategorieInstruktorowsByInstruktorId;
    }

    public void setKategorieInstruktorowsByInstruktorId(Collection<KategorieInstruktorowEntity> kategorieInstruktorowsByInstruktorId) {
        this.kategorieInstruktorowsByInstruktorId = kategorieInstruktorowsByInstruktorId;
    }

    @OneToMany(mappedBy = "instruktorzyByInstruktorId", orphanRemoval = true)
    public Collection<RezerwacjeEntity> getRezerwacjesByInstruktorId() {
        return rezerwacjesByInstruktorId;
    }

    public void setRezerwacjesByInstruktorId(Collection<RezerwacjeEntity> rezerwacjesByInstruktorId) {
        this.rezerwacjesByInstruktorId = rezerwacjesByInstruktorId;
    }
}
