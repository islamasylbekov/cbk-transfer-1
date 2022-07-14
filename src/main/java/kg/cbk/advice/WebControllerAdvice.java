package kg.cbk.advice;

import kg.cbk.entity.Employee;
import kg.cbk.entity.reference.CommonReference;
import kg.cbk.service.reference.CommonReferenceService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@ControllerAdvice(basePackages = {"kg.cbk.controller.web"})
public class WebControllerAdvice {

    private final CommonReferenceService referenceService;

    public WebControllerAdvice(CommonReferenceService referenceService) {
        this.referenceService = referenceService;
    }

    @ModelAttribute("user")
    public Employee user(@AuthenticationPrincipal Employee user) {
        return user;
    }

    @ModelAttribute("error")
    public String error(@RequestParam(value = "error", required = false) String error) {
        return error;
    }
}