package kg.cbk.endpoint.impl;

import kg.cbk.endpoint.EmployeeEndpoint;
import kg.cbk.entity.Employee;
import kg.cbk.mapper.EmployeeMapper;
import kg.cbk.models.employee.ChangePasswordForm;
import kg.cbk.models.employee.EmployeeDto;
import kg.cbk.service.employee.EmployeeService;
import kg.cbk.table.employee.EmployeeTable;
import kg.cbk.util.RedirectUtil;
import kg.cbk.validator.EmployeeFormValidator;
import kg.cbk.validator.PasswordFormValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

@Component
public class EmployeeEndpointImpl implements EmployeeEndpoint {

    private final static Logger LOGGER = LoggerFactory.getLogger(EmployeeEndpointImpl.class);

    private final EmployeeFormValidator employeeFormValidator;
    private final EmployeeTable employeeTable;
    private final EmployeeService employeeService;
    private final PasswordFormValidator passwordFormValidator;
    private final EmployeeMapper employeeMapper;

    public EmployeeEndpointImpl(
            EmployeeFormValidator employeeFormValidator,
            EmployeeTable employeeTable,
            EmployeeService employeeService,
            PasswordFormValidator passwordFormValidator,
            EmployeeMapper employeeMapper
    ) {
        this.employeeFormValidator = employeeFormValidator;
        this.employeeTable = employeeTable;
        this.employeeService = employeeService;
        this.passwordFormValidator = passwordFormValidator;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public ModelAndView list() {
        return new ModelAndView(String.format("%s/list", templates()))
                .addObject("table", employeeTable.build());
    }

    @Override
    public ModelAndView create() {
        return new ModelAndView(String.format("%s/create", templates()))
                .addObject("domain", new EmployeeDto());
    }

    @Override
    public ModelAndView create(EmployeeDto dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return new ModelAndView(String.format("%s/create", templates()))
                    .addObject("domain", dto);
        employeeService.save(employeeMapper.dtoToEntity(dto, new Employee()));
        return RedirectUtil.redirect(url());
    }

    @Override
    public ModelAndView edit(Employee entity) {
        return new ModelAndView(String.format("%s/edit", templates()))
                .addObject("domain", new EmployeeDto(entity));
    }

    @Override
    public ModelAndView edit(
            Employee entity,
            EmployeeDto dto,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors())
            return new ModelAndView(String.format("%s/edit", templates()))
                    .addObject("domain", dto);
        employeeService.save(employeeMapper.dtoToEntity(dto, entity));
        return RedirectUtil.redirect(url());
    }

    @Override
    public ModelAndView changePassword(Employee employee) {
        LOGGER.debug("Change password for: {}", employee.getUsername());
        ChangePasswordForm form = new ChangePasswordForm();
        form.setId(employee.getId());
        return new ModelAndView("employee/change-password")
                .addObject("domain", form);
    }

    @Override
    public ModelAndView changePassword(
            Employee employee,
            ChangePasswordForm form,
            BindingResult bindingResult
    ) {
        LOGGER.debug("Form: {}", form.toString());
        if (bindingResult.hasErrors()) {
            return new ModelAndView("employee/change-password")
                    .addObject("domain", form);
        }
        employeeService.changePassword(form, employee);
        LOGGER.debug("Change password - success!");
        return RedirectUtil.redirect("/employee");
    }

    public  String templates() {
        return "/employee";
    }

    public  String url() {
        return "/employee";
    }
}
