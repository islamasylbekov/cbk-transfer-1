package kg.cbk.endpoint;

import kg.cbk.entity.Employee;
import kg.cbk.models.employee.ChangePasswordForm;
import kg.cbk.models.employee.EmployeeDto;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

public interface EmployeeEndpoint {

    ModelAndView list();

    ModelAndView create();

    ModelAndView create(EmployeeDto dto, BindingResult bindingResult);

    ModelAndView edit(Employee entity);

    ModelAndView edit(Employee entity, EmployeeDto dto, BindingResult bindingResult);

    ModelAndView changePassword(Employee employee);

    ModelAndView changePassword(Employee employee, ChangePasswordForm form, BindingResult bindingResult);

}
