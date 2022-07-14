package kg.cbk.service.crud;

import kg.cbk.entity.base.BaseEntity;

import javax.validation.constraints.NotNull;

public interface FormCrudService<F, E extends BaseEntity> extends CrudService<E> {

    E create(
            @NotNull F f
    );

    E update(
            @NotNull F f,
            @NotNull E e
    );

}
