package kg.cbk.service;

import com.querydsl.core.types.Predicate;
import kg.cbk.entity.Employee;
import kg.cbk.entity.Transfer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TransferService {

    Page<Transfer> findAll(Predicate predicate, Pageable pageable, Employee employee);

    Transfer create(Transfer entity, Employee employee);

    Transfer edit(Transfer entity, Employee employee);

    Page<Transfer> findAll(Predicate predicate, Pageable pageable, Employee employee, String code);

    void getMoney(Transfer transfer, Employee employee);
}
