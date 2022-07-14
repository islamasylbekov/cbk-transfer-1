package kg.cbk.endpoint.impl;

import com.querydsl.core.types.Predicate;
import kg.cbk.assembler.SelectOptionResourceAssembler;
import kg.cbk.controller.web.ReferenceController;
import kg.cbk.endpoint.ReferenceEndpoint;
import kg.cbk.entity.Bank;
import kg.cbk.entity.Employee;
import kg.cbk.entity.reference.CommonReference;
import kg.cbk.entity.reference.CommonReferenceType;
import kg.cbk.entity.security.SecurityRole;
import kg.cbk.repository.reference.CommonReferenceTypeRepository;
import kg.cbk.resource.SelectOptionResource;
import kg.cbk.service.BankService;
import kg.cbk.service.employee.EmployeeService;
import kg.cbk.service.reference.CommonReferenceService;
import kg.cbk.service.security.SecurityRoleService;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class ReferenceEndpointImpl implements ReferenceEndpoint {

    private final CommonReferenceService commonReferenceService;
    private final CommonReferenceTypeRepository commonReferenceTypeRepository;
    private final EmployeeService employeeService;
    private final SecurityRoleService securityRoleService;
    private final BankService bankService;

    public ReferenceEndpointImpl(
            CommonReferenceService commonReferenceService,
            CommonReferenceTypeRepository commonReferenceTypeRepository,
            EmployeeService employeeService,
            SecurityRoleService securityRoleService,
            BankService bankService) {
        this.commonReferenceService = commonReferenceService;
        this.commonReferenceTypeRepository = commonReferenceTypeRepository;
        this.employeeService = employeeService;
        this.securityRoleService = securityRoleService;
        this.bankService = bankService;
    }


    @Override
    public PagedResources<SelectOptionResource> emptyList(
            Pageable page,
            PagedResourcesAssembler<Employee> pagedAssembler) {
        return pagedAssembler.toResource(new PageImpl<>(Collections.emptyList(), page, 0), new SelectOptionResourceAssembler<>(ReferenceController.class));
    }

    @Override
    public PagedResources<SelectOptionResource> employee(
            Employee employee,
            PagedResourcesAssembler<Employee> pagedAssembler,
            Predicate predicate, Pageable pageable
    ) {
        return pagedAssembler.toResource(
                employeeService.findAll(predicate, pageable, employee),
                new SelectOptionResourceAssembler<>(ReferenceController.class)
        );
    }

    @Override
    public PagedResources<SelectOptionResource> roles(
            PagedResourcesAssembler<SecurityRole> pagedAssembler,
            Predicate predicate, Pageable pageable
    ) {
        return pagedAssembler.toResource(
                securityRoleService.findAll(predicate, pageable),
                new SelectOptionResourceAssembler<>(ReferenceController.class)
        );
    }

    @Override
    public PagedResources<SelectOptionResource> commonReferenceType(
            PagedResourcesAssembler<CommonReferenceType> pagedAssembler,
            Predicate predicate,
            Pageable pageable
    ) {
        return pagedAssembler.toResource(
                commonReferenceTypeRepository.findAll(predicate, pageable),
                new SelectOptionResourceAssembler<>(ReferenceController.class)
        );
    }

    @Override
    public PagedResources<SelectOptionResource> commonReference(
            PagedResourcesAssembler<CommonReference> pagedAssembler,
            Predicate predicate, Pageable pageable)
    {
        return pagedAssembler.toResource(
                commonReferenceService.findAll(predicate, pageable),
                new SelectOptionResourceAssembler<>(ReferenceController.class)
        );
    }

    @Override
    public PagedResources<SelectOptionResource> commonReferenceParent(
            PagedResourcesAssembler<CommonReference> pagedAssembler,
            Predicate predicate,
            Pageable pageable
    ) {
        return pagedAssembler.toResource(
                commonReferenceService.findParents(predicate, pageable),
                new SelectOptionResourceAssembler<>(ReferenceController.class)
        );
    }

    @Override
    public PagedResources<SelectOptionResource> bank(
            Employee employee,
            PagedResourcesAssembler<Bank> pagedAssembler,
            Predicate predicate,
            Pageable pageable
    ) {
        return pagedAssembler.toResource(
                bankService.findAll(predicate, pageable, employee),
                new SelectOptionResourceAssembler<>(ReferenceController.class)
        );
    }
}
