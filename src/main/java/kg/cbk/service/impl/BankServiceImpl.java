package kg.cbk.service.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import kg.cbk.entity.Bank;
import kg.cbk.entity.Employee;
import kg.cbk.repository.BankRepository;
import kg.cbk.service.BankService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class BankServiceImpl implements BankService {

    private final BankRepository bankRepository;

    public BankServiceImpl(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    @Transactional(readOnly = true)
    public Page<Bank> findAll(
            Predicate predicate,
            Pageable pageable,
            Employee employee
    ) {
        BooleanBuilder builder = new BooleanBuilder(predicate);
        return bankRepository.findAll(builder.getValue(), pageable);
    }

    @Transactional
    @Override
    public Bank create(Bank entity) {
        return bankRepository.save(entity);
    }

    @Transactional
    @Override
    public Bank edit(Bank entity) {
        return bankRepository.save(entity);
    }

    @Transactional
    @Override
    public void moneyTransfer(Bank senderBank, Bank recipientBank, Long moneyCount) {
        senderBank.setMoneyCount(senderBank.getMoneyCount() - moneyCount);
        recipientBank.setMoneyCount(recipientBank.getMoneyCount() + moneyCount);
        bankRepository.saveAll(List.of(senderBank,recipientBank));
    }

}
