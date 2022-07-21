package kg.cbk.endpoint;

import com.querydsl.core.types.Predicate;
import kg.cbk.entity.Bank;
import kg.cbk.entity.Employee;
import kg.cbk.entity.reference.CommonReference;
import kg.cbk.entity.security.SecurityRole;
import kg.cbk.resource.SelectOptionResource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;

public interface ReferenceEndpoint {

    PagedResources<SelectOptionResource> emptyList(Pageable page,PagedResourcesAssembler<Employee> pagedAssembler);

    PagedResources<SelectOptionResource> employee(
            Employee employee,
            PagedResourcesAssembler<Employee> pagedAssembler,
            Predicate predicate,
            Pageable pageable);

    PagedResources<SelectOptionResource> roles(
            PagedResourcesAssembler<SecurityRole> pagedAssembler,
            Predicate predicate,
            Pageable pageable);

    PagedResources<SelectOptionResource> commonReference(
            PagedResourcesAssembler<CommonReference> pagedAssembler,
            Predicate predicate,
            Pageable pageable);

    PagedResources<SelectOptionResource> bank(
            Employee employee,
            PagedResourcesAssembler<Bank> pagedAssembler,
            Predicate predicate,
            Pageable pageable);
}
