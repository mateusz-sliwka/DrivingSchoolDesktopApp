package Entities;

import javax.persistence.*;

@Entity
@Table(name = "KATEGORIE_INSTRUKTOROW", schema = "SZKOLAJAZDY", catalog = "")
public class KategorieInstruktorowEntity {
    private long kategoriaId;
    private long instructorId;

    @Id
    @Column(name = "KATEGORIA_ID")
    public long getKategoriaId() {
        return kategoriaId;
    }

    public void setKategoriaId(long kategoriaId) {
        this.kategoriaId = kategoriaId;
    }
    @Basic
    @Column(name = "INSTRUCTOR_ID")
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
}
