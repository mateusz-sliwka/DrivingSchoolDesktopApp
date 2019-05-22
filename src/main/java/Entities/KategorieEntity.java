package Entities;

import javax.persistence.*;

@Entity
@Table(name = "KATEGORIE", schema = "SZKOLAJAZDY", catalog = "")
public class KategorieEntity {
    private long kategoriaId;
    private String symbol;

    @Id
    @Column(name = "KATEGORIA_ID")
    public long getKategoriaId() {
        return kategoriaId;
    }

    public void setKategoriaId(long kategoriaId) {
        this.kategoriaId = kategoriaId;
    }

    @Basic
    @Column(name = "SYMBOL")
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
}
