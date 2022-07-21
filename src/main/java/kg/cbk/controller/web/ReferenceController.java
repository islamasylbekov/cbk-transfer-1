package kg.cbk.controller.web;

import com.querydsl.core.types.Predicate;
import kg.cbk.endpoint.ReferenceEndpoint;
import kg.cbk.entity.Bank;
import kg.cbk.entity.Employee;
import kg.cbk.entity.reference.CommonReference;
import kg.cbk.entity.reference.CommonReferenceType;
import kg.cbk.entity.security.SecurityRole;
import kg.cbk.resource.SelectOptionResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reference/")
public class ReferenceController {

    private final ReferenceEndpoint referenceEndpoint;

    @Autowired
    public ReferenceController(ReferenceEndpoint referenceEndpoint) {
        this.referenceEndpoint = referenceEndpoint;
    }

    @GetMapping("/empty-list")
    public PagedResources<SelectOptionResource> emptyList(
            @PageableDefault Pageable page,
            PagedResourcesAssembler<Employee> pagedAssembler
    ) {
        return referenceEndpoint.emptyList(page, pagedAssembler);
    }

    @GetMapping("/employee")
    public PagedResources<SelectOptionResource> employee(
            @AuthenticationPrincipal Employee employee,
            PagedResourcesAssembler<Employee> pagedAssembler,
            @QuerydslPredicate(root = Employee.class) Predicate predicate,
            @PageableDefault Pageable pageable
    ) {
        return referenceEndpoint.employee(
                employee,
                pagedAssembler,
                predicate,
                pageable);
    }

    @GetMapping("/security-role")
    public PagedResources<SelectOptionResource> roles(
            PagedResourcesAssembler<SecurityRole> pagedAssembler,
            @QuerydslPredicate(root = SecurityRole.class) Predicate predicate,
            @PageableDefault Pageable pageable
    ) {
        return referenceEndpoint.roles(pagedAssembler, predicate, pageable);
    }

    @GetMapping("/common-reference")
    public PagedResources<SelectOptionResource> commonReference(
            PagedResourcesAssembler<CommonReference> pagedAssembler,
            @QuerydslPredicate(root = CommonReference.class) Predicate predicate,
            @PageableDefault Pageable pageable
    ) {
        return referenceEndpoint.commonReference(pagedAssembler, predicate, pageable);
    }

    @GetMapping("/bank")
    public PagedResources<SelectOptionResource> bank(
            @AuthenticationPrincipal Employee employee,
            PagedResourcesAssembler<Bank> pagedAssembler,
            @QuerydslPredicate(root = Bank.class) Predicate predicate,
            @PageableDefault Pageable pageable
    ) {
        return referenceEndpoint.bank(employee, pagedAssembler, predicate, pageable);
    }

}
