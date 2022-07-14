package kg.cbk.endpoint;

import kg.cbk.entity.Employee;
import kg.cbk.entity.Transfer;
import kg.cbk.models.TransferDto;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

public interface TransferEndpoint {

    ModelAndView list();

    ModelAndView list(String code);

    ModelAndView create();

    ModelAndView create(TransferDto dto,BindingResult bindingResult, Employee employee);

    ModelAndView edit( Transfer entity);

    ModelAndView edit(Transfer entity, TransferDto dto,BindingResult bindingResult, Employee employee);

    ModelAndView getMoney(Long id);

    ModelAndView getMoney(Transfer transfer, String code, Employee employee);
}
