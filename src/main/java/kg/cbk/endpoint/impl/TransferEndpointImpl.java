package kg.cbk.endpoint.impl;

import kg.cbk.endpoint.TransferEndpoint;
import kg.cbk.entity.Employee;
import kg.cbk.entity.Transfer;
import kg.cbk.mapper.TransferMapper;
import kg.cbk.models.CodeConfirmDto;
import kg.cbk.models.TransferDto;
import kg.cbk.service.TransferService;
import kg.cbk.service.reference.CommonReferenceService;
import kg.cbk.table.TransferTable;
import kg.cbk.util.RedirectUtil;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

@Component
public class TransferEndpointImpl implements TransferEndpoint {

    private final TransferTable table;
    private final TransferService service;
    private final TransferMapper mapper;
    private final CommonReferenceService referenceService;

    public TransferEndpointImpl(
            TransferTable table,
            TransferService service,
            TransferMapper mapper,
            CommonReferenceService referenceService
    ) {
        this.table = table;
        this.service = service;
        this.mapper = mapper;
        this.referenceService = referenceService;
    }

    @Override
    public ModelAndView list() {
        return new ModelAndView(String.format("%s/list", templates()))
                .addObject("table", table.build())
                .addObject("statuses", referenceService.findByTypeCode("STATUS"));
    }

    @Override
    public ModelAndView list(String code) {
        return new ModelAndView(String.format("%s/list", templates()))
                .addObject("table", table.setCode(code))
                .addObject("statuses", referenceService.findByTypeCode("STATUS"));
    }

    @Override
    public ModelAndView create() {
        return new ModelAndView(String.format("%s/create", templates()))
                .addObject("domain", new TransferDto());
    }

    @Override
    public ModelAndView create(
            TransferDto dto,
            BindingResult bindingResult,
            Employee employee
    ) {
        if (bindingResult.hasErrors())
            return new ModelAndView(String.format("%s/create", templates()))
                    .addObject("domain", dto);
        service.create(mapper.entityToDto(dto), employee);
        return RedirectUtil.redirect(url());
    }

    @Override
    public ModelAndView edit(Transfer entity) {
        return new ModelAndView(String.format("%s/edit", templates()))
                .addObject("domain", mapper.dtoToEntity(entity));
    }

    @Override
    public ModelAndView edit(
            Transfer entity,
            TransferDto dto,
            BindingResult bindingResult,
            Employee employee
    ) {
        if (bindingResult.hasErrors())
            return new ModelAndView(String.format("%s/edit", templates()))
                    .addObject("domain", dto);
        service.edit(mapper.dtoToEntity(dto, entity), employee);
        return RedirectUtil.redirect(url());
    }

    @Override
    public ModelAndView getMoney(Long id) {
        return new ModelAndView(String.format("%s/getMoney", templates()))
                .addObject("domain", new CodeConfirmDto(id));
    }

    @Override
    public ModelAndView getMoney(
            Transfer transfer,
            String code,
            Employee employee
    ) {
        if (!transfer.getCode().equals(code))
            return new ModelAndView(String.format("%s/getMoney", templates()))
                    .addObject("error", "error")
                    .addObject("domain", new CodeConfirmDto(transfer.getId()));
        service.getMoney(transfer, employee);
        return RedirectUtil.redirect(url());
    }

    public String templates() {
        return "/transfer";
    }

    public String url() {
        return "/transfer";
    }
}
