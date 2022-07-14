package kg.cbk.mapper.impl;

import kg.cbk.entity.Employee;
import kg.cbk.mapper.EmployeeMapper;
import kg.cbk.models.employee.EmployeeDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class EmployeeMapperImpl implements EmployeeMapper {

    private final PasswordEncoder passwordEncoder;

    public EmployeeMapperImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public EmployeeDto entityToDto(Employee entity) {
        return null;
    }

    @Override
    public Employee dtoToEntity(EmployeeDto dto, Employee employee) {
        employee.setSurname(dto.getSurname());
        employee.setName(dto.getName());
        employee.setPatronymic(dto.getPatronymic());
        employee.setLogin(dto.getLogin());
        employee.setPassword(passwordEncoder.encode(dto.getPassword()));
        employee.setDateOfBirth(dto.getDateOfBirth());
        employee.setRank(dto.getRank());
        employee.setRole(dto.getRole());
        employee.setRoles(Stream.concat(Optional.ofNullable(dto.getRoles()).orElse(Set.of()).stream(), Set.of(dto.getRole()).stream()).collect(Collectors.toSet()));
        employee.setEnabled(true);
        employee.setPasswordExpirationTime(LocalDateTime.now());
        employee.setBank(dto.getBank());
        return employee;
    }
}
