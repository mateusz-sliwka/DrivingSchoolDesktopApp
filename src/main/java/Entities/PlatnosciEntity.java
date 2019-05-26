package Entities;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name = "PLATNOSCI", schema = "SZKOLAJAZDY", catalog = "")
public class PlatnosciEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PLATNOSC_ID")
    private long platnoscId;
    private Long kwota;
    private Time dataPlatnosci;
    private RezerwacjeEntity rezerwacjeByRezerwacjaId;

    @Basic
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
    public Long getKwota() {
        return kwota;
    }

    public void setKwota(Long kwota) {
        this.kwota = kwota;
    }

    @Basic
    @Column(name = "DATA_PLATNOSCI", nullable = true)
    public Time getDataPlatnosci() {
        return dataPlatnosci;
    }

    public void setDataPlatnosci(Time dataPlatnosci) {
        this.dataPlatnosci = dataPlatnosci;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlatnosciEntity that = (PlatnosciEntity) o;
        if (platnoscId != that.platnoscId) return false;
        if (kwota != null ? !kwota.equals(that.kwota) : that.kwota != null) return false;
        if (dataPlatnosci != null ? !dataPlatnosci.equals(that.dataPlatnosci) : that.dataPlatnosci != null)
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (platnoscId ^ (platnoscId >>> 32));
        result = 31 * result + (kwota != null ? kwota.hashCode() : 0);
        result = 31 * result + (dataPlatnosci != null ? dataPlatnosci.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "REZERWACJA_ID", referencedColumnName = "REZERWACJA_ID", nullable = false)
    public RezerwacjeEntity getRezerwacjeByRezerwacjaId() {
        return rezerwacjeByRezerwacjaId;
    }

    public void setRezerwacjeByRezerwacjaId(RezerwacjeEntity rezerwacjeByRezerwacjaId) {
        this.rezerwacjeByRezerwacjaId = rezerwacjeByRezerwacjaId;
    }
}
