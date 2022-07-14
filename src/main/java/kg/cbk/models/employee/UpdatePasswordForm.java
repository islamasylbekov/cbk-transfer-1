package kg.cbk.models.employee;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class UpdatePasswordForm {

    @NotEmpty
    private String username;

    @NotEmpty
    private String oldPassword;

    @NotEmpty
    @Size(min = 3)
    private String newPassword;

    @NotEmpty
    @Size(min = 3)
    private String confirmPassword;

}
