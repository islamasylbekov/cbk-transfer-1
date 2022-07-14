package kg.cbk.repository;

import kg.cbk.entity.Bank;
import kg.cbk.entity.QBank;
import kg.cbk.entity.QEmployee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import javax.validation.constraints.NotNull;
import java.util.Optional;


public interface BankRepository extends JpaRepository<Bank, Long>,
        QuerydslPredicateExecutor<Bank>, QuerydslBinderCustomizer<QBank> {
    @Override
    default void customize(@NotNull QuerydslBindings bindings, @NotNull QBank root) {

    }
}
