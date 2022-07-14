package kg.cbk.service.security;

import kg.cbk.entity.Employee;
import kg.cbk.entity.security.SecurityRole;
import kg.cbk.service.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class AppUserDetailsService implements UserDetailsService {

    private final EmployeeService employeeService;

    @Autowired
    public AppUserDetailsService(
            EmployeeService employeeService
    ) {
        this.employeeService = employeeService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = employeeService.findByLogin(username);

        if (employee == null)
            throw new UsernameNotFoundException(String.format("Employee with login %s not found", username));

        Set<SecurityRole> roles = employee.getRoles();
        roles.add(employee.getRole());
        employee.setSRoles(roles);

        employee
                .setSAuthorities(
                        employee.getSRoles()
                                .stream()
                                .map(SecurityRole::getCode)
                                .map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
        return employee;
    }


}