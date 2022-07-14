package kg.cbk.mapper;

import kg.cbk.entity.Employee;
import kg.cbk.models.employee.EmployeeDto;

public interface EmployeeMapper {

    EmployeeDto entityToDto(Employee entity);

    Employee dtoToEntity(EmployeeDto dto, Employee employee);

}
