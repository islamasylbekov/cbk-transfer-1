package kg.cbk.controller.web;

import kg.cbk.endpoint.BankEndpoint;
import kg.cbk.entity.Bank;
import kg.cbk.entity.Employee;
import kg.cbk.models.BankDto;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Secured("ROLE_ADMIN")
@Controller
@RequestMapping("/bank")
public class BankController{

    private final BankEndpoint bankEndpoint;

    public BankController(BankEndpoint bankEndpoint) {
        this.bankEndpoint = bankEndpoint;
    }

    @GetMapping
    public ModelAndView list() {
        return bankEndpoint.list();
    }

    @GetMapping("/create")
    public ModelAndView create() {
        return bankEndpoint.create();
    }

    @PostMapping("/create")
    public ModelAndView create(
            @Valid @ModelAttribute("domain") BankDto dto,
            @AuthenticationPrincipal Employee employee,
            BindingResult bindingResult
    ) {
        return bankEndpoint.create(dto, employee, bindingResult);
    }

    @GetMapping("/edit")
    public ModelAndView edit(
            @RequestParam("id") Bank entity
    ) {
        return bankEndpoint.edit(entity);
    }

    @PostMapping("/edit")
    public ModelAndView edit(
            @RequestParam("id") Bank entity,
            @Valid @ModelAttribute("domain") BankDto dto,
            BindingResult bindingResult
    ) {
        return bankEndpoint.edit(entity, dto, bindingResult);
    }
}
