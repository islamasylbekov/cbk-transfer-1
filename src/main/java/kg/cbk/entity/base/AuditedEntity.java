package kg.cbk.entity.base;

import kg.cbk.entity.Employee;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AuditedEntity extends TimedEntity {

    private static final long serialVersionUID = -1509276355914518719L;

    @CreatedBy
    @ManyToOne
    @JoinColumn(name = "created_by")
    private Employee createdBy;

    @LastModifiedBy
    @ManyToOne
    @JoinColumn(name = "updated_by")
    private Employee updatedBy;

}
