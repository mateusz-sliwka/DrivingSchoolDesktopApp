package Entities;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "PLATNOSCI", schema = "SZKOLAJAZDY", catalog = "")
public class PlatnosciEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PLATNOSC_ID")
    private long platnoscId;
    private long kwota;
    private Date dataPlatnosci;
    private long kursantId;
    private KursanciEntity kursanciByKursantId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PLATNOSC_ID", nullable = false, precision = 0)
    public long getPlatnoscId() {
        return platnoscId;
    }

    public void setPlatnoscId(long platnoscId) {
        this.platnoscId = platnoscId;
    }

    @Basic
    @Column(name = "KWOTA", nullable = true, precision = 0)
    public long getKwota() {
        return kwota;
    }

    public void setKwota(long kwota) {
        this.kwota = kwota;
    }

    @Basic
    @Column(name = "DATA_PLATNOSCI", nullable = true)
    public Date getDataPlatnosci() {
        return dataPlatnosci;
    }

    public void setDataPlatnosci(Date dataPlatnosci) {
        this.dataPlatnosci = dataPlatnosci;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlatnosciEntity that = (PlatnosciEntity) o;
        if (platnoscId != that.platnoscId) return false;
        if (dataPlatnosci != null ? !dataPlatnosci.equals(that.dataPlatnosci) : that.dataPlatnosci != null)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (platnoscId ^ (platnoscId >>> 32));
        result = 31 * result + (dataPlatnosci != null ? dataPlatnosci.hashCode() : 0);
        return result;
    }


    @Basic
    @Column(name = "KURSANT_ID", nullable = false, precision = 0)
    public long getKursantId() {
        return kursantId;
    }

    public void setKursantId(long kursantId) {
        this.kursantId = kursantId;
    }

    @ManyToOne
    @JoinColumn(name = "KURSANT_ID", referencedColumnName = "KURSANT_ID", nullable = false, updatable = false, insertable = false)
    public KursanciEntity getKursanciByKursantId() {
        return kursanciByKursantId;
    }

    public void setKursanciByKursantId(KursanciEntity kursanciByKursantId) {
        this.kursanciByKursantId = kursanciByKursantId;
    }
}
