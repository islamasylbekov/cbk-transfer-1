package kg.cbk.service;

import com.querydsl.core.types.Predicate;
import kg.cbk.entity.Bank;
import kg.cbk.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BankService {

    Page<Bank> findAll(Predicate predicate, Pageable pageable, Employee employee);

    Bank create(Bank entity);

    Bank edit(Bank entity);

    void moneyTransfer(Bank senderBank, Bank recipientBank, Long moneyCount);
}
