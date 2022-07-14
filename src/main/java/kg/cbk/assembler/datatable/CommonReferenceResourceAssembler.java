package kg.cbk.assembler.datatable;

import kg.cbk.assembler.datatable.base.DataTableResourceAssembler;
import kg.cbk.entity.reference.CommonReference;
import kg.cbk.resource.datatable.ReferenceResource;
import org.springframework.stereotype.Component;

@Component
public class CommonReferenceResourceAssembler extends DataTableResourceAssembler<CommonReference, ReferenceResource> {

    public CommonReferenceResourceAssembler() {
        super(CommonReference.class, ReferenceResource.class);
    }

    @Override
    public ReferenceResource toResource(CommonReference entity) {
        ReferenceResource resource = createResourceWithId(entity.getId(), entity);

        resource.id = entity.getId().toString();

        resource.setType(entity.getType().getSelectorTitle());
        resource.setCode(entity.getCode());
        resource.setTitle(entity.getTitle());

        return resource;
    }

}