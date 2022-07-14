package kg.cbk.entity.reference;

import kg.cbk.component.Selectable;
import kg.cbk.entity.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "ref_common_reference_type")
public class CommonReferenceType extends BaseEntity implements Selectable {

    private static final long serialVersionUID = -282960210449513007L;

    @NotNull
    private String title;

    private String code;

    @Override
    public String getSelectorId() {
        return String.valueOf(getId());
    }

    @Override
    public String getSelectorTitle() {
        return title;
    }

}
