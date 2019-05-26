package Entities;

import javax.persistence.*;

@Entity
@Table(name = "HIBERNATE_SEQUENCES", schema = "SZKOLAJAZDY", catalog = "")
public class HibernateSequencesEntity {
    private String sequenceName;
    private Long nextVal;

    @Id
    @Column(name = "SEQUENCE_NAME", nullable = false, length = 255)
    public String getSequenceName() {
        return sequenceName;
    }

    public void setSequenceName(String sequenceName) {
        this.sequenceName = sequenceName;
    }

    @Basic
    @Column(name = "NEXT_VAL", nullable = true, precision = 0)
    public Long getNextVal() {
        return nextVal;
    }

    public void setNextVal(Long nextVal) {
        this.nextVal = nextVal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HibernateSequencesEntity that = (HibernateSequencesEntity) o;
        if (sequenceName != null ? !sequenceName.equals(that.sequenceName) : that.sequenceName != null) return false;
        if (nextVal != null ? !nextVal.equals(that.nextVal) : that.nextVal != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = sequenceName != null ? sequenceName.hashCode() : 0;
        result = 31 * result + (nextVal != null ? nextVal.hashCode() : 0);
        return result;
    }
}
