package kg.cbk.controller.api;

import com.querydsl.core.types.Predicate;
import kg.cbk.assembler.datatable.BankResourceAssembler;
import kg.cbk.entity.Bank;
import kg.cbk.entity.Employee;
import kg.cbk.resource.datatable.BankResource;
import kg.cbk.resource.datatable.EmployeeResource;
import kg.cbk.resource.datatable.base.TableResource;
import kg.cbk.service.BankService;
import kg.cbk.table.core.TableRequestData;
import org.springframework.data.domain.Page;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bank")
public class BankApiController {

    private final BankService bankService;
    private final BankResourceAssembler employeeResourceAssembler;

    public BankApiController(
            BankService bankService,
            BankResourceAssembler employeeResourceAssembler) {
        this.bankService = bankService;
        this.employeeResourceAssembler = employeeResourceAssembler;
    }


    @PostMapping("/table")
    public TableResource<BankResource> employees(
            @AuthenticationPrincipal Employee employee,
            @QuerydslPredicate(root = Bank.class) Predicate predicate,
            @RequestBody TableRequestData requestData
    ) {
        return employeeResourceAssembler.toTableResource(
                bankService.findAll(predicate, requestData.getPageable(), employee),
                requestData
        );
    }
}
