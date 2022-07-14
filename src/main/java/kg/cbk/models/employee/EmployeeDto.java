package kg.cbk.models.employee;

import kg.cbk.entity.Bank;
import kg.cbk.entity.Employee;
import kg.cbk.entity.reference.CommonReference;
import kg.cbk.entity.security.SecurityRole;
import kg.cbk.util.TimeHelper;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@Data
@NoArgsConstructor
@SuperBuilder
public class EmployeeDto {

    private Long id;

    @NotEmpty
    private String login;

    private String password;

    @NotEmpty
    private String surname;

    @NotEmpty
    private String name;

    private String patronymic;

    @NotNull
    @DateTimeFormat(pattern = TimeHelper.DATE_FORMAT)
    private LocalDate dateOfBirth;

    @NotNull
    private SecurityRole role;

    private Set<SecurityRole> roles;

    private boolean enabled;

    private String rank;

    private CommonReference gender;

    private Bank bank;

    public EmployeeDto(Employee employee){
        this.id = employee.getId();
        this.login = employee.getLogin();
        this.surname = employee.getSurname();
        this.name = employee.getName();
        this.patronymic = Optional.ofNullable(employee.getPatronymic()).orElse("");
        this.dateOfBirth = employee.getDateOfBirth();
        this.role = employee.getRole();
        this.roles = employee.getRoles();
        this.enabled = employee.isEnabled();
        this.rank = employee.getRank();
        this.bank = employee.getBank();
    }
}
