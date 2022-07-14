package kg.cbk.entity.reference;

import kg.cbk.component.Selectable;
import kg.cbk.entity.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "ref_common_reference")
public class CommonReference extends BaseEntity implements Selectable {

    private static final long serialVersionUID = 1519273511390280643L;

    @NotNull
    private String title;

    @NotNull
    private String code;

    @Column(columnDefinition = "boolean default true")
    private boolean enabled;

    @ManyToOne
    @JoinColumn(name = "parent_id", insertable = false, updatable = false)
    private CommonReference parent;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "type_id", insertable = false, updatable = false)
    private CommonReferenceType type;

    @Override
    public String getSelectorId() {
        return String.valueOf(getId());
    }

    @Override
    public String getSelectorTitle() {
        return title;
    }
}
