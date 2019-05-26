package Entities;

import Controlers.KursanciControler;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Collection;

@Entity
@Table(name = "KURSANCI", schema = "SZKOLAJAZDY", catalog = "")
public class KursanciEntity implements Serializable {

    @Id
    @Column(name = "KURSANT_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long kursantId;
    private String imie;
    private String nazwisko;
    private String pesel;
    private String email;
    private String pkk;
    private String haslo;
    private Date dataRejestracji;
    private Collection<RezerwacjeEntity> rezerwacjesByKursantId;
    private Collection<PlatnosciEntity> platnoscisByKursantId;

    @Id
    @Column(name = "KURSANT_ID", nullable = false, precision = 0)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getKursantId() {
        return kursantId;
    }

    public void setKursantId(long kursantId) {
        this.kursantId = kursantId;
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
    @Column(name = "PESEL", nullable = true, length = 20)
    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
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
    @Column(name = "PKK", nullable = true, length = 20)
    public String getPkk() {
        return pkk;
    }

    public void setPkk(String pkk) {
        this.pkk = pkk;
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
    @Column(name = "DATA_REJESTRACJI", nullable = true)
    public Date getDataRejestracji() {
        return dataRejestracji;
    }

    public void setDataRejestracji(Date dataRejestracji) {
        this.dataRejestracji = dataRejestracji;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KursanciEntity that = (KursanciEntity) o;
        if (kursantId != that.kursantId) return false;
        if (imie != null ? !imie.equals(that.imie) : that.imie != null) return false;
        if (nazwisko != null ? !nazwisko.equals(that.nazwisko) : that.nazwisko != null) return false;
        if (pesel != null ? !pesel.equals(that.pesel) : that.pesel != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (pkk != null ? !pkk.equals(that.pkk) : that.pkk != null) return false;
        if (haslo != null ? !haslo.equals(that.haslo) : that.haslo != null) return false;
        if (dataRejestracji != null ? !dataRejestracji.equals(that.dataRejestracji) : that.dataRejestracji != null)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (kursantId ^ (kursantId >>> 32));
        result = 31 * result + (imie != null ? imie.hashCode() : 0);
        result = 31 * result + (nazwisko != null ? nazwisko.hashCode() : 0);
        result = 31 * result + (pesel != null ? pesel.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (pkk != null ? pkk.hashCode() : 0);
        result = 31 * result + (haslo != null ? haslo.hashCode() : 0);
        result = 31 * result + (dataRejestracji != null ? dataRejestracji.hashCode() : 0);
        return result;
    }

    @Transient
    public String getImieNazwisko() {
        return this.imie + " " + this.nazwisko;
    }

    @OneToMany(mappedBy = "kursanciByKursantId", orphanRemoval = true)
    public Collection<RezerwacjeEntity> getRezerwacjesByKursantId() {
        return rezerwacjesByKursantId;
    }

    public void setRezerwacjesByKursantId(Collection<RezerwacjeEntity> rezerwacjesByKursantId) {
        this.rezerwacjesByKursantId = rezerwacjesByKursantId;
    }

    @OneToMany(mappedBy = "kursanciByKursantId", orphanRemoval = true)
    public Collection<PlatnosciEntity> getPlatnoscisByKursantId() {
        return platnoscisByKursantId;
    }

    public void setPlatnoscisByKursantId(Collection<PlatnosciEntity> platnoscisByKursantId) {
        this.platnoscisByKursantId = platnoscisByKursantId;
    }
}
