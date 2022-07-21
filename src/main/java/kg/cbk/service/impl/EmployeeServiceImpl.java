package kg.cbk.service.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import kg.cbk.configuration.Constants;
import kg.cbk.entity.Employee;
import kg.cbk.models.employee.ChangePasswordForm;
import kg.cbk.models.employee.UpdatePasswordForm;
import kg.cbk.repository.EmployeeRepository;
import kg.cbk.service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    final PasswordEncoder passwordEncoder;
    final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(
            PasswordEncoder passwordEncoder,
            EmployeeRepository employeeRepository
    ) {
        this.passwordEncoder = passwordEncoder;
        this.employeeRepository = employeeRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Employee> findAll(
            Predicate predicate,
            Pageable pageable,
            Employee employee
    ) {
        BooleanBuilder builder = new BooleanBuilder(predicate);
        return employeeRepository.findAll(builder.getValue(), pageable);
    }

    @Transactional
    @Override
    public void updatePasswordAndExtendExpirationTime(
            @NotNull UpdatePasswordForm form,
            @NotNull Employee employee
    ) {
        employee.setPassword(passwordEncoder.encode(form.getNewPassword()));
        employee.setPasswordExpirationTime(LocalDateTime.now().plusDays(Constants.PASSWORD_EXPIRATION_DAY_COUNT));
        save(employee);
    }

    @Transactional
    @Override
    public void changePassword(
            @NotNull ChangePasswordForm form,
            @NotNull Employee employee
    ) {
        employee.setPassword(passwordEncoder.encode(form.getNewPassword()));
        employee.setPasswordExpirationTime(LocalDateTime.now());
        save(employee);
    }

    @Transactional(readOnly = true)
    @Override
    public Employee findByLogin(String login) {
        return employeeRepository.findByLogin(login);
    }

    @Transactional
    @Override
    public Employee save(Employee employee){
        return employeeRepository.save(employee);
    }
}
