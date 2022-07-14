package kg.cbk.models;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CodeConfirmDto {

    @NotBlank
    private String code;

    private Long id;

    public CodeConfirmDto(Long id) {
        this.id = id;
    }
}
