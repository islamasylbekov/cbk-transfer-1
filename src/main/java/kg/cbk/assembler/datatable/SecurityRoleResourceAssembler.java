package kg.cbk.assembler.datatable;

import kg.cbk.assembler.datatable.base.DataTableResourceAssembler;
import kg.cbk.controller.api.EmployeeControllerApi;
import kg.cbk.entity.security.SecurityRole;
import kg.cbk.resource.datatable.SecurityRoleResource;
import org.springframework.stereotype.Component;

@Component
public class SecurityRoleResourceAssembler extends DataTableResourceAssembler<SecurityRole, SecurityRoleResource> {

    public SecurityRoleResourceAssembler() {
        super(EmployeeControllerApi.class, SecurityRoleResource.class);
    }

    @Override
    public SecurityRoleResource toResource(SecurityRole entity) {
        SecurityRoleResource resource = createResourceWithId(entity.getId(), entity);

        resource.id = String.format("<a href='/security/role/%d/edit'>%d</a>", entity.getId(), entity.getId());

        resource.setTitle(entity.getTitle());
        resource.setCode(entity.getCode());

        return resource;
    }

}