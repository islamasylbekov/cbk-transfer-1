package kg.cbk.controller.web;

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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/transfer")
public class TransferController {

    private final TransferEndpoint transferEndpoint;

    public TransferController(TransferEndpoint transferEndpoint) {
        this.transferEndpoint = transferEndpoint;
    }

    @GetMapping()
    public ModelAndView list() {
        return transferEndpoint.list();
    }

    @GetMapping("/{code}")
    public ModelAndView list(@PathVariable("code") String code) {
        return transferEndpoint.list(code);
    }

    @GetMapping("/create")
    public ModelAndView create() {
        return transferEndpoint.create();
    }

    @PostMapping("/create")
    public ModelAndView create(
            @Valid @ModelAttribute("domain") TransferDto dto,
            BindingResult bindingResult,
            @AuthenticationPrincipal Employee employee
    ) {
        return transferEndpoint.create(dto, bindingResult, employee);
    }

    @GetMapping("/edit")
    public ModelAndView edit(
            @RequestParam("id") Transfer entity
    ) {
        return transferEndpoint.edit(entity);
    }

    @PostMapping("/edit")
    public ModelAndView edit(
            @RequestParam("id") Transfer entity,
            @Valid @ModelAttribute("domain") TransferDto dto,
            BindingResult bindingResult,
            @AuthenticationPrincipal Employee employee
    ) {
        return transferEndpoint.edit(entity, dto, bindingResult, employee);
    }

    @GetMapping("/getMoney")
    public ModelAndView getMoney(
            @RequestParam("id") Long id
    ) {
        return transferEndpoint.getMoney(id);
    }

    @PostMapping("/getMoney")
    public ModelAndView getMoney(
            @RequestParam("id") Transfer transfer,
            @RequestParam("code") String code,
            @AuthenticationPrincipal Employee employee
    ) {
        return transferEndpoint.getMoney(transfer, code, employee);
    }
}
