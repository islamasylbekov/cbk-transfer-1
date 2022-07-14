package kg.cbk.models;

import kg.cbk.entity.Bank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@SuperBuilder
@NoArgsConstructor
public class BankDto {

    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String address;

    @NotNull
    private Long moneyCount;
}
