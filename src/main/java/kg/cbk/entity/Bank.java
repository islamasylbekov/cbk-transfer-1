package kg.cbk.entity;

import kg.cbk.component.Selectable;
import kg.cbk.entity.base.TimedEntity;
import lombok.AllArgsConstructor;
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
public class Bank extends TimedEntity implements Selectable {

    @NotBlank
    private String name;

    @NotBlank
    private String address;

    @NotNull
    @Column(name = "money_count")
    private Long moneyCount;

    public static BankBuilder builder() {
        return new BankBuilder();
    }

    @Override
    public String getSelectorId() {
        return super.getId().toString();
    }

    @Override
    public String getSelectorTitle() {
        return this.getName();
    }

    public static class BankBuilder {
        private @NotBlank String name;
        private @NotBlank String address;
        private @NotNull Long moneyCount;

        BankBuilder() {
        }

        public BankBuilder name(@NotBlank String name) {
            this.name = name;
            return this;
        }

        public BankBuilder address(@NotBlank String address) {
            this.address = address;
            return this;
        }

        public BankBuilder moneyCount(@NotNull Long moneyCount) {
            this.moneyCount = moneyCount;
            return this;
        }

        public Bank build() {
            return new Bank(name, address, moneyCount);
        }

        public String toString() {
            return "Bank.BankBuilder(name=" + this.name + ", address=" + this.address + ", moneyCount=" + this.moneyCount + ")";
        }
    }
}
