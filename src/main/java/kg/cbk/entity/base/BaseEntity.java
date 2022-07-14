package kg.cbk.entity.base;

import com.querydsl.core.annotations.PropertyType;
import com.querydsl.core.annotations.QueryType;
import lombok.Getter;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Audited
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 8430941711005097202L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    @QueryType(value = PropertyType.STRING)
    private transient String q;

    public boolean isNew() {
        return Objects.isNull(getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseEntity entity = (BaseEntity) o;
        return id.equals(entity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
