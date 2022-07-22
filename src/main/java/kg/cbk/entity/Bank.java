package kg.cbk.entity;

import kg.cbk.component.Selectable;
import kg.cbk.entity.base.TimedEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "bank")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bank extends TimedEntity implements Selectable {

    @NotBlank
    private String name;

    @NotBlank
    private String address;

    @NotNull
    @Column(name = "money_count")
    private Long moneyCount;


    @Override
    public String getSelectorId() {
        return super.getId().toString();
    }

    @Override
    public String getSelectorTitle() {
        return this.getName();
    }

}
