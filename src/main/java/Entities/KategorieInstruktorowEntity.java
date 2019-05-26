package Entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "KATEGORIE_INSTRUKTOROW", schema = "SZKOLAJAZDY", catalog = "")
public class KategorieInstruktorowEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_WPISU")
    private long idWpisu;
    private long kategoriaId;
    private long instructorId;

    private KategorieEntity kategorieByKategoriaId;
    private InstruktorzyEntity instruktorzyByKategoriaId;

    @Basic
    @Column(name = "KATEGORIA_ID", nullable = false, precision = 0)
    public long getKategoriaId() {
        return kategoriaId;
    }

    public void setKategoriaId(long kategoriaId) {
        this.kategoriaId = kategoriaId;
    }

    @Basic
    @Column(name = "INSTRUCTOR_ID", nullable = false, precision = 0)
    public long getInstructorId() {
        return instructorId;
    }
    public void setInstructorId(long instructorId) {
        this.instructorId = instructorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KategorieInstruktorowEntity that = (KategorieInstruktorowEntity) o;
        if (instructorId != that.instructorId) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return (int) (instructorId ^ (instructorId >>> 32));
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_WPISU", nullable = false, precision = 0)
    public long getIdWpisu() {
        return idWpisu;
    }

    public void setIdWpisu(long idWpisu) {
        this.idWpisu = idWpisu;
    }

    @ManyToOne
    @JoinColumn(name = "KATEGORIA_ID", referencedColumnName = "KATEGORIA_ID", nullable = false,insertable=false ,updatable=false)
    public KategorieEntity getKategorieByKategoriaId() {
        return kategorieByKategoriaId;
    }

    public void setKategorieByKategoriaId(KategorieEntity kategorieByKategoriaId) {
        this.kategorieByKategoriaId = kategorieByKategoriaId;
    }

    @ManyToOne
    @JoinColumn(name = "KATEGORIA_ID", referencedColumnName = "INSTRUKTOR_ID", nullable = false,insertable=false ,updatable=false)
    public InstruktorzyEntity getInstruktorzyByKategoriaId() {
        return instruktorzyByKategoriaId;
    }

    public void setInstruktorzyByKategoriaId(InstruktorzyEntity instruktorzyByKategoriaId) {
        this.instruktorzyByKategoriaId = instruktorzyByKategoriaId;
    }
}
