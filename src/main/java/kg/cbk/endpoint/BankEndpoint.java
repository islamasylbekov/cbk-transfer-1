package kg.cbk.endpoint;

import kg.cbk.entity.Bank;
import kg.cbk.entity.Employee;
import kg.cbk.models.BankDto;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

public interface BankEndpoint {

    ModelAndView list();

    ModelAndView create();

    ModelAndView create(BankDto form, Employee employee, BindingResult bindingResult);

    ModelAndView edit(Bank entity);

    ModelAndView edit(Bank entity, BankDto form, BindingResult bindingResult);
}
