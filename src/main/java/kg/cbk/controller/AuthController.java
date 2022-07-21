package kg.cbk.controller;

import kg.cbk.entity.Employee;
import kg.cbk.service.EmployeeService;
import kg.cbk.models.employee.UpdatePasswordForm;
import kg.cbk.util.RedirectUtil;
import kg.cbk.validator.PasswordFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final EmployeeService employeeService;
    private final PasswordFormValidator passwordFormValidator;

    @Autowired
    public AuthController(
            EmployeeService employeeService,
            PasswordFormValidator passwordFormValidator
    ) {
        this.employeeService = employeeService;
        this.passwordFormValidator = passwordFormValidator;
    }

    @InitBinder("auth")
    public void updatePasswordValidator(WebDataBinder webDataBinder){
        if(webDataBinder.getTarget() instanceof UpdatePasswordForm){
            webDataBinder.addValidators(passwordFormValidator);
        }
    }

    @GetMapping("/login")
    public ModelAndView login(
            @RequestParam(value = "error", required = false) Boolean error
    ) {
        return new ModelAndView("auth/login")
                .addObject("error", error);
    }

    @GetMapping("/update-password")
    public ModelAndView updatePassword(
            @RequestParam("username") String username
    ) {
        UpdatePasswordForm form = new UpdatePasswordForm();
        form.setUsername(username);
        return new ModelAndView("auth/update-password")
                .addObject("auth", form);
    }

    @PostMapping("/update-password")
    public ModelAndView updatePassword(
            @Valid @ModelAttribute("auth") UpdatePasswordForm form,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors())
            return new ModelAndView("auth/update-password")
                    .addObject("auth", form);
        Employee employee = employeeService.findByLogin(form.getUsername());
        employeeService.updatePasswordAndExtendExpirationTime(form, employee);
        return RedirectUtil.redirect("/auth/login");
    }
}
