package kg.cbk.controller.api;

import com.querydsl.core.types.Predicate;
import kg.cbk.entity.Employee;
import kg.cbk.service.employee.EmployeeService;
import kg.cbk.assembler.datatable.EmployeeResourceAssembler;
import kg.cbk.resource.datatable.EmployeeResource;
import kg.cbk.resource.datatable.base.TableResource;
import kg.cbk.table.core.TableRequestData;
import org.springframework.data.domain.Page;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employee")
public class EmployeeControllerApi {

    private final EmployeeService employeeService;
    private final EmployeeResourceAssembler employeeResourceAssembler;

    public EmployeeControllerApi(
            EmployeeService employeeService,
            EmployeeResourceAssembler employeeResourceAssembler
    ) {
        this.employeeService = employeeService;
        this.employeeResourceAssembler = employeeResourceAssembler;
    }

    @PostMapping("/table")
    public TableResource<EmployeeResource> employees(
            @AuthenticationPrincipal Employee employee,
            @QuerydslPredicate(root = Employee.class) Predicate predicate,
            @RequestBody TableRequestData requestData
    ) {
        return employeeResourceAssembler.toTableResource(
                employeeService.findAll(predicate, requestData.getPageable(), employee),
                requestData
        );
    }
}
