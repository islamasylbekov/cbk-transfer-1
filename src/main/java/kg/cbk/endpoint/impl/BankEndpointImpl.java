package kg.cbk.endpoint.impl;

import kg.cbk.endpoint.BankEndpoint;
import kg.cbk.entity.Bank;
import kg.cbk.entity.Employee;
import kg.cbk.mapper.BankMapper;
import kg.cbk.models.BankDto;
import kg.cbk.service.BankService;
import kg.cbk.table.BankTable;
import kg.cbk.util.RedirectUtil;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
@Component
public class BankEndpointImpl implements BankEndpoint {

    private final BankTable bankTable;
    private final BankService bankService;
    private final BankMapper mapper;

    public BankEndpointImpl(
            BankTable bankTable,
            BankService bankService,
            BankMapper mapper
    ) {
        this.bankTable = bankTable;
        this.bankService = bankService;
        this.mapper = mapper;
    }

    @Override
    public ModelAndView list() {
        return new ModelAndView(String.format("%s/list", templates()))
                .addObject("table", bankTable.build());
    }

    @Override
    public ModelAndView create() {
        return new ModelAndView(String.format("%s/create", templates()))
                .addObject("domain", new BankDto());
    }

    @Override
    public ModelAndView create(
            BankDto form,
            Employee employee,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors())
            return new ModelAndView(String.format("%s/create", templates()))
                    .addObject("domain", form);
        Bank bank = mapper.entityToDto(form);
        bankService.create(bank);
        return RedirectUtil.redirect(url());
    }

    @Override
    public ModelAndView edit(Bank entity) {
        return new ModelAndView(String.format("%s/edit", templates()))
                .addObject("domain", mapper.dtoToEntity(entity));
    }

    @Override
    public ModelAndView edit(
            Bank entity,
            BankDto form,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors())
            return new ModelAndView(String.format("%s/edit", templates()))
                    .addObject("domain", form);
        bankService.edit(mapper.dtoToEntity(form, entity));
        return RedirectUtil.redirect(url());
    }

    public String templates() {
        return "/bank";
    }

    public String url() {
        return "/bank";
    }
}
