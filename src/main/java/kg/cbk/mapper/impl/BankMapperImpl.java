package kg.cbk.mapper.impl;

import kg.cbk.entity.Bank;
import kg.cbk.mapper.BankMapper;
import kg.cbk.models.BankDto;
import org.springframework.stereotype.Component;

@Component
public class BankMapperImpl implements BankMapper {
    @Override
    public Bank entityToDto(BankDto bankDto) {
        return Bank.builder()
                .address(bankDto.getAddress())
                .moneyCount(bankDto.getMoneyCount())
                .name(bankDto.getName())
                .build();
    }

    @Override
    public BankDto dtoToEntity(Bank bank) {
        return BankDto.builder()
                .id(bank.getId())
                .address(bank.getAddress())
                .moneyCount(bank.getMoneyCount())
                .name(bank.getName())
                .build();
    }

    @Override
    public Bank dtoToEntity(BankDto form, Bank entity) {
        entity.setName(form.getName());
        entity.setAddress(form.getAddress());
        entity.setMoneyCount(form.getMoneyCount());
        return entity;
    }
}
