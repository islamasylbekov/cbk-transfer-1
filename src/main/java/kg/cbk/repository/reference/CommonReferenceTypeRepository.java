package kg.cbk.repository.reference;

import kg.cbk.entity.reference.CommonReferenceType;
import kg.cbk.entity.reference.QCommonReferenceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import javax.validation.constraints.NotNull;

public interface CommonReferenceTypeRepository extends JpaRepository<CommonReferenceType, Long>,
        QuerydslPredicateExecutor<CommonReferenceType>, QuerydslBinderCustomizer<QCommonReferenceType> {

    @Override
    default void customize(@NotNull QuerydslBindings bindings, @NotNull QCommonReferenceType root) {
        bindings.bind(root.q).first((path, value) -> root.title.containsIgnoreCase(value));
    }

}
