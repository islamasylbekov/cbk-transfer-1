package kg.cbk.validator;

import kg.cbk.service.EmployeeService;
import kg.cbk.models.employee.EmployeeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.constraints.NotNull;
import java.util.Objects;

@Component
public class EmployeeFormValidator implements Validator {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeFormValidator(
            EmployeeService employeeService
    ) {
        this.employeeService = employeeService;
    }

    @Override
    public boolean supports(@NotNull Class<?> aClass) {
        return EmployeeDto.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, @NotNull Errors errors) {
        final EmployeeDto form = (EmployeeDto) o;

        if (Objects.isNull(form.getId()) && (form.getPassword().isEmpty() || Objects.isNull(form.getPassword())))
            errors.rejectValue("password", "not-empty");

        if (Objects.isNull(form.getId()) && Objects.nonNull(employeeService.findByLogin(form.getLogin())))
            errors.rejectValue("login", "already-exist");
    }

}
