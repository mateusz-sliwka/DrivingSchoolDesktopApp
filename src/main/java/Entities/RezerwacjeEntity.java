package Entities;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "REZERWACJE", schema = "SZKOLAJAZDY", catalog = "")
public class RezerwacjeEntity {
    private long rezerwacjaId;
    private Time dataDodania;
    private Long uslugaId;
    private Long kursantId;
    private Long instruktorId;
    private Long instruktorid;
    private Long kursantid;
    private Long uslugaid;

    @Id
    @Column(name = "REZERWACJA_ID")
    public long getRezerwacjaId() {
        return rezerwacjaId;
    }

    public void setRezerwacjaId(long rezerwacjaId) {
        this.rezerwacjaId = rezerwacjaId;
    }

    @Basic
    @Column(name = "DATA_DODANIA")
    public Time getDataDodania() {
        return dataDodania;
    }

    public void setDataDodania(Time dataDodania) {
        this.dataDodania = dataDodania;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RezerwacjeEntity that = (RezerwacjeEntity) o;
        if (rezerwacjaId != that.rezerwacjaId) return false;
        if (dataDodania != null ? !dataDodania.equals(that.dataDodania) : that.dataDodania != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (rezerwacjaId ^ (rezerwacjaId >>> 32));
        result = 31 * result + (dataDodania != null ? dataDodania.hashCode() : 0);
        return result;
    }

    @Basic
    public Long getUslugaId() {
        return uslugaId;
    }

    public void setUslugaId(Long uslugaId) {
        this.uslugaId = uslugaId;
    }

    @Basic
    public Long getKursantId() {
        return kursantId;
    }

    public void setKursantId(Long kursantId) {
        this.kursantId = kursantId;
    }

    @Basic
    public Long getInstruktorId() {
        return instruktorId;
    }

    public void setInstruktorId(Long instruktorId) {
        this.instruktorId = instruktorId;
    }

    @Basic
    @Column(name = "INSTRUKTORID")
    public Long getInstruktorid() {
        return instruktorid;
    }

    public void setInstruktorid(Long instruktorid) {
        this.instruktorid = instruktorid;
    }

    @Basic
    @Column(name = "KURSANTID")
    public Long getKursantid() {
        return kursantid;
    }

    public void setKursantid(Long kursantid) {
        this.kursantid = kursantid;
    }

    @Basic
    @Column(name = "USLUGAID")
    public Long getUslugaid() {
        return uslugaid;
    }

    public void setUslugaid(Long uslugaid) {
        this.uslugaid = uslugaid;
    }
}
