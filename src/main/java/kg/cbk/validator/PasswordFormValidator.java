package kg.cbk.validator;

import kg.cbk.entity.Employee;
import kg.cbk.service.employee.EmployeeService;
import kg.cbk.models.employee.ChangePasswordForm;
import kg.cbk.models.employee.UpdatePasswordForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.NotNull;

@Component
public class PasswordFormValidator implements Validator {

    private final EmployeeService employeeService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PasswordFormValidator(
            EmployeeService employeeService,
            PasswordEncoder passwordEncoder
    ) {
        this.employeeService = employeeService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean supports(@NotNull Class<?> aClass) {
        return UpdatePasswordForm.class.isAssignableFrom(aClass) || ChangePasswordForm.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, @NotNull Errors errors) {
        if(o instanceof UpdatePasswordForm) {
            UpdatePasswordForm form = (UpdatePasswordForm) o;

            Employee employee = employeeService.findByLogin(form.getUsername());
            if (employee == null) {
                errors.rejectValue("username", "not-found");
            } else {
                if (!passwordEncoder.matches(form.getOldPassword(), employee.getPassword())) {
                    errors.rejectValue("oldPassword", "not-matches");
                }

                if (!form.getNewPassword().equals(form.getConfirmPassword())) {
                    errors.rejectValue("confirmPassword", "not-matches");
                }
            }
        } else if(o instanceof ChangePasswordForm){
            ChangePasswordForm form = (ChangePasswordForm) o;

            if (!form.getNewPassword().equals(form.getConfirmPassword())) {
                errors.rejectValue("confirmPassword", "not-matches");
            }
        }
    }

}
