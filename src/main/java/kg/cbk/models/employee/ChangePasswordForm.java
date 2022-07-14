package kg.cbk.models.employee;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ChangePasswordForm {

    @NotNull
    private Long id;

    @NotEmpty
    @Size(min = 3)
    private String newPassword;

    @NotEmpty
    @Size(min = 3)
    private String confirmPassword;

}
