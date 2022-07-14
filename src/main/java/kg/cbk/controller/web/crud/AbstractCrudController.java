package kg.cbk.controller.web.crud;

import kg.cbk.entity.base.BaseEntity;
import kg.cbk.service.crud.FormCrudService;

import javax.validation.constraints.NotNull;

public abstract class AbstractCrudController<F, E extends BaseEntity> implements CrudController {

    protected final FormCrudService<F, E> service;

    public AbstractCrudController(
            @NotNull FormCrudService<F, E> service
    ) {
        this.service = service;
    }

}
