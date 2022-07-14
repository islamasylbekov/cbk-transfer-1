package kg.cbk.repository.security;

import kg.cbk.entity.security.QSecurityRole;
import kg.cbk.entity.security.SecurityRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import javax.validation.constraints.NotNull;

public interface SecurityRoleRepository extends JpaRepository<SecurityRole, Long>,
        QuerydslPredicateExecutor<SecurityRole>, QuerydslBinderCustomizer<QSecurityRole> {

    @Override
    default void customize(@NotNull QuerydslBindings bindings, @NotNull QSecurityRole root) {
        bindings.bind(root.q).first((path, value) -> root.title.containsIgnoreCase(value));
    }

    SecurityRole findByCode(String code);

}
