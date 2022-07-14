package kg.cbk.models;

import kg.cbk.entity.Bank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@SuperBuilder
@NoArgsConstructor
public class TransferDto {

    private Long id;

    @NotBlank
    private String senderName;

    @NotBlank
    private String senderSurname;

    @NotBlank
    private String senderPatronymic;

    private Bank senderBank;

    @NotBlank
    private String recipientName;

    @NotBlank
    private String recipientSurname;

    @NotBlank
    private String recipientPatronymic;

    private Bank recipientBank;

    @NotNull
    private Long moneyCount;

    private String code;

}
