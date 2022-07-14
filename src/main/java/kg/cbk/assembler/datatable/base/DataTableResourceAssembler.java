package kg.cbk.assembler.datatable.base;

import kg.cbk.entity.base.BaseEntity;
import kg.cbk.table.core.TableRequestData;
import kg.cbk.resource.datatable.base.BaseResource;
import kg.cbk.resource.datatable.base.TableResource;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import java.util.List;

abstract public class DataTableResourceAssembler<T extends BaseEntity, D extends BaseResource> extends ResourceAssemblerSupport<T, D> {

    public DataTableResourceAssembler(Class<?> controllerClass, Class<D> resourceType) {
        super(controllerClass, resourceType);
    }

    public TableResource<D> toTableResource(Page<T> page, TableRequestData requestData) {
        TableResource<D> tableResource = new TableResource<>();

        List<D> data = toResources(page.getContent());

        tableResource.setDraw(requestData.getDraw() + 1);
        tableResource.setRecordsTotal(page.getTotalElements());
        tableResource.setRecordsFiltered(page.getTotalElements());
        tableResource.setData(data);

        return tableResource;
    }

    @Override
    protected D createResourceWithId(Object id, T entity) {
        D d = super.createResourceWithId(id, entity);
        d.setUuid(entity.getId().toString());
        return d;
    }
}
