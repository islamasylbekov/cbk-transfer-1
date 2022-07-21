package kg.cbk.endpoint.impl;

import com.querydsl.core.types.Predicate;
import kg.cbk.assembler.SelectOptionResourceAssembler;
import kg.cbk.controller.web.ReferenceController;
import kg.cbk.endpoint.ReferenceEndpoint;
import kg.cbk.entity.Bank;
import kg.cbk.entity.Employee;
import kg.cbk.entity.reference.CommonReference;
import kg.cbk.entity.security.SecurityRole;
import kg.cbk.repository.reference.CommonReferenceTypeRepository;
import kg.cbk.repository.security.SecurityRoleRepository;
import kg.cbk.resource.SelectOptionResource;
import kg.cbk.service.BankService;
import kg.cbk.service.EmployeeService;
import kg.cbk.service.CommonReferenceService;
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

    private final SecurityRoleRepository securityRoleRepository;

    private final BankService bankService;

    public ReferenceEndpointImpl(
            CommonReferenceService commonReferenceService,
            CommonReferenceTypeRepository commonReferenceTypeRepository,
            EmployeeService employeeService,
            SecurityRoleRepository securityRoleRepository, BankService bankService) {
        this.commonReferenceService = commonReferenceService;
        this.commonReferenceTypeRepository = commonReferenceTypeRepository;
        this.employeeService = employeeService;
        this.securityRoleRepository = securityRoleRepository;
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
                securityRoleRepository.findAll(predicate, pageable),
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
