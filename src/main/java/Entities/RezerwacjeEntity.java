package Entities;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.Collection;

@Entity
@Table(name = "REZERWACJE", schema = "SZKOLAJAZDY", catalog = "")
public class RezerwacjeEntity implements Serializable {
    @Id
    @Column(name = "REZERWACJA_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long rezerwacjaId;
    private Date dataDodania;
    private Long uslugaId;
    private Long kursantId;
    private Long instruktorId;
    private Collection<PlatnosciEntity> platnoscisByRezerwacjaId;
    private UslugiEntity uslugiByUslugaId;
    private KursanciEntity kursanciByKursantId;
    private InstruktorzyEntity instruktorzyByInstruktorId;

    @Id
    @Column(name = "REZERWACJA_ID", nullable = false, precision = 0)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getRezerwacjaId() {
        return rezerwacjaId;
    }

    public void setRezerwacjaId(long rezerwacjaId) {
        this.rezerwacjaId = rezerwacjaId;
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

    @OneToMany(mappedBy = "rezerwacjeByRezerwacjaId",orphanRemoval=true)
    public Collection<PlatnosciEntity> getPlatnoscisByRezerwacjaId() {
        return platnoscisByRezerwacjaId;
    }

    public void setPlatnoscisByRezerwacjaId(Collection<PlatnosciEntity> platnoscisByRezerwacjaId) {
        this.platnoscisByRezerwacjaId = platnoscisByRezerwacjaId;
    }

    @ManyToOne
    @JoinColumn(name = "USLUGA_ID", referencedColumnName = "USLUGA_ID", nullable = false,insertable = false, updatable = false)
    public UslugiEntity getUslugiByUslugaId() {
        return uslugiByUslugaId;
    }

    public void setUslugiByUslugaId(UslugiEntity uslugiByUslugaId) {
        this.uslugiByUslugaId = uslugiByUslugaId;
    }

    @ManyToOne
    @JoinColumn(name = "KURSANT_ID", referencedColumnName = "KURSANT_ID", nullable = false,insertable = false,updatable = false)
    public KursanciEntity getKursanciByKursantId() {
        return kursanciByKursantId;
    }

    public void setKursanciByKursantId(KursanciEntity kursanciByKursantId) {
        this.kursanciByKursantId = kursanciByKursantId;
    }

    @ManyToOne
    @JoinColumn(name = "INSTRUKTOR_ID", referencedColumnName = "INSTRUKTOR_ID", nullable = false,insertable=false ,updatable=false)
    public InstruktorzyEntity getInstruktorzyByInstruktorId() {
        return instruktorzyByInstruktorId;
    }

    public void setInstruktorzyByInstruktorId(InstruktorzyEntity instruktorzyByInstruktorId) {
        this.instruktorzyByInstruktorId = instruktorzyByInstruktorId;
    }
}
