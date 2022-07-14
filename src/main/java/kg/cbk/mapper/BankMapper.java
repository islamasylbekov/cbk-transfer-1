package kg.cbk.mapper;

import kg.cbk.entity.Bank;
import kg.cbk.models.BankDto;

import javax.validation.Valid;

public interface BankMapper {

    Bank entityToDto(BankDto bankDto);

    BankDto dtoToEntity(Bank bank);

    Bank dtoToEntity(BankDto form, Bank entity);
}
