package kg.cbk.assembler.datatable;

import kg.cbk.assembler.datatable.base.DataTableResourceAssembler;
import kg.cbk.controller.api.EmployeeControllerApi;
import kg.cbk.entity.Employee;
import kg.cbk.resource.datatable.EmployeeResource;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
public class EmployeeResourceAssembler extends DataTableResourceAssembler<Employee, EmployeeResource> {

    public EmployeeResourceAssembler() {
        super(EmployeeControllerApi.class, EmployeeResource.class);
    }

    @Override
    public EmployeeResource toResource(Employee entity) {
        EmployeeResource resource = createResourceWithId(entity.getId(), entity);
        resource.id = entity.getSelectorId();
        resource.setSurname(entity.getSurname());
        resource.setName(entity.getName());
        resource.setLogin(entity.getLogin());
        resource.setRole(Objects.nonNull(entity.getRole()) ? entity.getRole().getSelectorTitle() : "");
        Optional.ofNullable(entity.getRank()).ifPresent(resource::setRank);
        return resource;
    }
}