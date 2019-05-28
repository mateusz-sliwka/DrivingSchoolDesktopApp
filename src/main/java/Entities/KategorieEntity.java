package Entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "KATEGORIE", schema = "SZKOLAJAZDY", catalog = "")
public class KategorieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "KATEGORIA_ID")
    private long kategoriaId;
    private String symbol;
    private Collection<KategorieInstruktorowEntity> kategorieInstruktorowsByKategoriaId;
    private Collection<RezerwacjeEntity> rezerwacjesByKategoriaId;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "KATEGORIA_ID", nullable = false, precision = 0)
    public long getKategoriaId() {
        return kategoriaId;
    }

    public void setKategoriaId(long kategoriaId) {
        this.kategoriaId = kategoriaId;
    }

    @Basic
    @Column(name = "SYMBOL", nullable = true, length = 5)
    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KategorieEntity that = (KategorieEntity) o;
        if (kategoriaId != that.kategoriaId) return false;
        if (symbol != null ? !symbol.equals(that.symbol) : that.symbol != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (kategoriaId ^ (kategoriaId >>> 32));
        result = 31 * result + (symbol != null ? symbol.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "kategorieByKategoriaId", orphanRemoval = true)
    public Collection<KategorieInstruktorowEntity> getKategorieInstruktorowsByKategoriaId() {
        return kategorieInstruktorowsByKategoriaId;
    }

    public void setKategorieInstruktorowsByKategoriaId(Collection<KategorieInstruktorowEntity> kategorieInstruktorowsByKategoriaId) {
        this.kategorieInstruktorowsByKategoriaId = kategorieInstruktorowsByKategoriaId;
    }

    @OneToMany(mappedBy = "kategorieByKategoriaId", orphanRemoval = true)
    public Collection<RezerwacjeEntity> getRezerwacjesByKategoriaId() {
        return rezerwacjesByKategoriaId;
    }

    public void setRezerwacjesByKategoriaId(Collection<RezerwacjeEntity> rezerwacjesByKategoriaId) {
        this.rezerwacjesByKategoriaId = rezerwacjesByKategoriaId;
    }
}
