package kg.cbk.repository;

import kg.cbk.entity.Employee;
import kg.cbk.entity.QEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import javax.validation.constraints.NotNull;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long>,
        QuerydslPredicateExecutor<Employee>, QuerydslBinderCustomizer<QEmployee> {

    @Override
    default void customize(@NotNull QuerydslBindings bindings, @NotNull QEmployee root) {
        bindings.bind(root.qRole).all((stringPath, collection) ->
                Optional.ofNullable(root.roles.any().code.in(collection)));
        bindings.bind(root.q).first((path, value) ->
                root.surname.concat(" ").concat(root.name).containsIgnoreCase(value));
    }

    Employee findByLogin(String login);

}
