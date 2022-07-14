package kg.cbk.entity.security;

import kg.cbk.component.Selectable;
import kg.cbk.entity.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@Entity
public class SecurityRole extends BaseEntity implements Serializable, Selectable {

    private static final long serialVersionUID = -4884852760501686700L;

    @NotNull
    private String title;

    @NotNull
    private String code;

    @NotNull
    @Column(name = "default_page")
    private String defaultPage;

    @Override
    public String getSelectorId() {
        return String.valueOf(getId());
    }

    @Override
    public String getSelectorTitle() {
        return getTitle();
    }
}
