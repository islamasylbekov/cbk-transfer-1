package kg.cbk.repository.reference;

import kg.cbk.entity.reference.CommonReference;
import kg.cbk.entity.reference.QCommonReference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface CommonReferenceRepository extends JpaRepository<CommonReference, Long>,
        QuerydslPredicateExecutor<CommonReference>, QuerydslBinderCustomizer<QCommonReference> {

    @Override
    default void customize(@NotNull QuerydslBindings bindings, @NotNull QCommonReference root) {
        bindings.bind(root.q).first((path, value) ->
                root.code.containsIgnoreCase(value).or(root.title.containsIgnoreCase(value)));
    }

    List<CommonReference> findByCode(String code);

    List<CommonReference> findByTypeCode(String code);

}
