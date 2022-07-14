package kg.cbk.controller.web;

import kg.cbk.endpoint.EmployeeEndpoint;
import kg.cbk.entity.Employee;
import kg.cbk.models.employee.ChangePasswordForm;
import kg.cbk.models.employee.EmployeeDto;
import kg.cbk.validator.EmployeeFormValidator;
import kg.cbk.validator.PasswordFormValidator;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Secured("ROLE_ADMIN")
@Controller
@RequestMapping("/employee")
public class EmployeeController{

    private final EmployeeFormValidator employeeFormValidator;
    private final PasswordFormValidator passwordFormValidator;
    private final EmployeeEndpoint endpoint;

    public EmployeeController(
            EmployeeFormValidator employeeFormValidator,
            PasswordFormValidator passwordFormValidator,
            EmployeeEndpoint endpoint
    ) {
        this.employeeFormValidator = employeeFormValidator;
        this.passwordFormValidator = passwordFormValidator;
        this.endpoint = endpoint;
    }

    @InitBinder("domain")
    public void formValidator(WebDataBinder webDataBinder) {
        if(webDataBinder.getTarget() instanceof EmployeeDto) {
            webDataBinder.addValidators(employeeFormValidator);
        } else if(webDataBinder.getTarget() instanceof ChangePasswordForm){
            webDataBinder.addValidators(passwordFormValidator);
        }
    }

    @GetMapping
    public ModelAndView list() {
        return endpoint.list();
    }

    @GetMapping("/create")
    public ModelAndView create() {
        return endpoint.create();
    }

    @PostMapping("/create")
    public ModelAndView create(
            @Valid @ModelAttribute("domain") EmployeeDto dto,
            BindingResult bindingResult
    ) {
        return endpoint.create(dto, bindingResult);
    }

    @GetMapping("/edit")
    public ModelAndView edit(
            @RequestParam("id") Employee entity
    ) {
        return endpoint.edit(entity);
    }

    @PostMapping("/edit")
    public ModelAndView edit(
            @RequestParam("id") Employee entity,
            @Valid @ModelAttribute("domain") EmployeeDto dto,
            BindingResult bindingResult
    ) {
        return endpoint.edit(entity,dto,bindingResult);
    }

    @GetMapping("/change-password")
    public ModelAndView changePassword(
            @RequestParam("id") Employee employee
    ) {
        return endpoint.changePassword(employee);
    }

    @PostMapping("/change-password")
    public ModelAndView changePassword(
            @RequestParam("id") Employee employee,
            @Valid @ModelAttribute("domain") ChangePasswordForm form,
            BindingResult bindingResult
    ) {
       return endpoint.changePassword(employee, form, bindingResult);
    }
}
