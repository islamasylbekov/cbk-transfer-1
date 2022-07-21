package kg.cbk.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import kg.cbk.configuration.Constants;
import kg.cbk.entity.Employee;
import kg.cbk.models.employee.ChangePasswordForm;
import kg.cbk.models.employee.UpdatePasswordForm;
import kg.cbk.repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public interface EmployeeService {

    Page<Employee> findAll(
            Predicate predicate,
            Pageable pageable,
            Employee employee);

    void updatePasswordAndExtendExpirationTime(UpdatePasswordForm form, Employee employee);
    void changePassword(ChangePasswordForm form, Employee employee);

    Employee findByLogin(String login);

    Employee save(Employee employee);
}
