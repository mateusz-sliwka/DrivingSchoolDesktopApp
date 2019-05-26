package Entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "REZERWACJE", schema = "SZKOLAJAZDY", catalog = "")
public class RezerwacjeEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.TABLE)
    private long rezerwacjaId;
    private Date dataDodania;
    private Long uslugaId;
    private Long kursantId;
    private Long instruktorId;

    @Id
    @Column(name = "REZERWACJA_ID")
    @GeneratedValue(strategy=GenerationType.TABLE)
    public long getRezerwacjaId() {
        return rezerwacjaId;
    }

    public void setRezerwacjaId(long rezerwacjaId) {
        this.rezerwacjaId = rezerwacjaId;
    }

    @Basic
    @Column(name = "DATA_DODANIA")
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
    @Column(name = "USLUGA_ID")
    public Long getUslugaId() {
        return uslugaId;
    }

    public void setUslugaId(Long uslugaId) {
        this.uslugaId = uslugaId;
    }

    @Basic
    @Column(name = "KURSANT_ID")
    public Long getKursantId() {
        return kursantId;
    }

    public void setKursantId(Long kursantId) {
        this.kursantId = kursantId;
    }

    @Basic
    @Column(name = "INSTRUKTOR_ID")
    public Long getInstruktorId() {
        return instruktorId;
    }

    public void setInstruktorId(Long instruktorId) {
        this.instruktorId = instruktorId;
    }


}
