package kg.cbk.endpoint;

import kg.cbk.entity.Bank;
import kg.cbk.entity.Employee;
import kg.cbk.models.BankDto;
import kg.cbk.util.RedirectUtil;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

public interface BankEndpoint {

    ModelAndView list();

    ModelAndView create();

    ModelAndView create(BankDto form, Employee employee, BindingResult bindingResult);

    ModelAndView edit(Bank entity);

    ModelAndView edit(Bank entity, BankDto form, BindingResult bindingResult);
}
