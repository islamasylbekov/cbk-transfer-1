package kg.cbk.service.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import kg.cbk.entity.Employee;
import kg.cbk.entity.QTransfer;
import kg.cbk.entity.Transfer;
import kg.cbk.repository.TransferRepository;
import kg.cbk.service.BankService;
import kg.cbk.service.TransferService;
import kg.cbk.service.CommonReferenceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class TransferServiceImpl implements TransferService {

    private final TransferRepository repository;
    private final CommonReferenceService commonReferenceService;
    private final BankService bankService;

    public TransferServiceImpl(
            TransferRepository repository,
            CommonReferenceService commonReferenceService,
            BankService bankService) {
        this.repository = repository;
        this.commonReferenceService = commonReferenceService;
        this.bankService = bankService;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Transfer> findAll(Predicate predicate, Pageable pageable, Employee employee) {
        return repository.findAll(predicate,pageable);
    }

    @Transactional
    @Override
    public Transfer create(Transfer entity, Employee employee) {
        entity.setCode(UUID.randomUUID().toString().split("-")[0]);
        entity.setSenderBank(employee.getBank());
        entity.setStatus(commonReferenceService.getFirstFindByCode("SENT"));
        return repository.save(entity);
    }

    @Transactional
    @Override
    public Transfer edit(Transfer entity, Employee employee) {
        entity.setSenderBank(employee.getBank());
        return repository.save(entity);
    }

    @Transactional
    @Override
    public Page<Transfer> findAll(
            Predicate predicate,
            Pageable pageable,
            Employee employee,
            String code
    ) {
        if (code == null)
            return repository.findAll(predicate,pageable);
        BooleanBuilder booleanBuilder = new BooleanBuilder(predicate);
        QTransfer root = QTransfer.transfer;
        booleanBuilder.and(root.status.code.eq(code));
        return repository.findAll(booleanBuilder.getValue(),pageable);
    }

    @Transactional
    @Override
    public void getMoney(Transfer transfer, Employee employee) {
        transfer.setRecipientBank(employee.getBank());
        transfer.setStatus(commonReferenceService.getFirstFindByCode("RECEIVED"));
        bankService.moneyTransfer(
                transfer.getSenderBank(),
                transfer.getRecipientBank(),
                transfer.getMoneyCount());
        repository.save(transfer);
    }
}
