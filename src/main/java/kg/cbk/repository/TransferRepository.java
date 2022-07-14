package kg.cbk.repository;


import kg.cbk.entity.QBank;
import kg.cbk.entity.QEmployee;
import kg.cbk.entity.QTransfer;
import kg.cbk.entity.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import javax.validation.constraints.NotNull;
import java.util.Optional;

public interface TransferRepository extends JpaRepository<Transfer, Long>,
        QuerydslPredicateExecutor<Transfer>, QuerydslBinderCustomizer<QTransfer> {

    @Override
    default void customize(@NotNull QuerydslBindings bindings, @NotNull QTransfer root) {
    }
}
