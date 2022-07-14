package kg.cbk.assembler;

import kg.cbk.component.Selectable;
import kg.cbk.resource.SelectOptionResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

public class SelectOptionResourceAssembler<T extends Selectable> extends ResourceAssemblerSupport<T, SelectOptionResource> {

    public SelectOptionResourceAssembler(Class<?> controllerClass) {
        super(controllerClass, SelectOptionResource.class);
    }

    @Override
    public SelectOptionResource toResource(T selectable) {
        SelectOptionResource resource = createResourceWithId(selectable.getSelectorId(), selectable);

        resource.id = (selectable.getSelectorId());
        resource.setTitle(selectable.getSelectorTitle());

        return resource;
    }
}
