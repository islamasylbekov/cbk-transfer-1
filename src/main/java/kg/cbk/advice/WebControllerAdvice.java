package kg.cbk.advice;

import kg.cbk.entity.Employee;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@ControllerAdvice(basePackages = {"kg.cbk.controller.web"})
public class WebControllerAdvice {

    @ModelAttribute("user")
    public Employee user(@AuthenticationPrincipal Employee user) {
        return user;
    }

    @ModelAttribute("error")
    public String error(@RequestParam(value = "error", required = false) String error) {
        return error;
    }
}