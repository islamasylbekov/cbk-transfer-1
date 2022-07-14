package kg.cbk.entity.audit;

import kg.cbk.entity.Employee;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import javax.persistence.*;

@Entity
@Table(name = "revision")
@RevisionEntity(AppRevisionListener.class)
public class RevEntity {

    @Id
    @RevisionNumber
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @RevisionTimestamp
    private long timestamp;

    @ManyToOne
    @JoinColumn(name = "auditor_id")
    private Employee auditor;

    public RevEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Employee getAuditor() {
        return auditor;
    }

    public void setAuditor(Employee auditor) {
        this.auditor = auditor;
    }
}