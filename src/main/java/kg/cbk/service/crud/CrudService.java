package kg.cbk.service.crud;

import com.querydsl.core.types.Predicate;
import kg.cbk.entity.Employee;
import kg.cbk.entity.base.BaseEntity;
import kg.cbk.exception.NotImplementedException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotNull;

public interface CrudService<E extends BaseEntity> {

    @NotNull
    Page<E> findAll(
            Predicate predicate,
            @NotNull Pageable pageable
    );

    @NotNull
    default Page<E> findAll(
            Predicate predicate,
            @NotNull Pageable pageable,
            @NotNull Employee employee
    ) {
        throw new NotImplementedException();
    }

    @NotNull
    E save(
            @NotNull E e
    );

    void delete(
            @NotNull E e
    );
}
