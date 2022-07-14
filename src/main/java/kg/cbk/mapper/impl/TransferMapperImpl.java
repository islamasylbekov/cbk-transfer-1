package kg.cbk.mapper.impl;

import kg.cbk.entity.Transfer;
import kg.cbk.mapper.TransferMapper;
import kg.cbk.models.TransferDto;
import org.springframework.stereotype.Component;

@Component
public class TransferMapperImpl implements TransferMapper {

    @Override
    public Transfer entityToDto(TransferDto dto) {
        return Transfer.builder()
                .moneyCount(dto.getMoneyCount())
                .code(dto.getCode())
                .recipientBank(dto.getRecipientBank())
                .recipientSurname(dto.getRecipientSurname())
                .recipientName(dto.getRecipientName())
                .recipientPatronymic(dto.getRecipientPatronymic())
                .senderBank(dto.getSenderBank())
                .senderName(dto.getSenderName())
                .senderSurname(dto.getSenderSurname())
                .senderPatronymic(dto.getSenderPatronymic())
                .build();
    }

    @Override
    public TransferDto dtoToEntity(Transfer entity) {
        return TransferDto.builder()
                .moneyCount(entity.getMoneyCount())
                .code(entity.getCode())
                .recipientBank(entity.getRecipientBank())
                .recipientSurname(entity.getRecipientSurname())
                .recipientName(entity.getRecipientName())
                .recipientPatronymic(entity.getRecipientPatronymic())
                .senderBank(entity.getSenderBank())
                .senderName(entity.getSenderName())
                .senderSurname(entity.getSenderSurname())
                .senderPatronymic(entity.getSenderPatronymic())
                .id(entity.getId())
                .build();
    }

    @Override
    public Transfer dtoToEntity(TransferDto dto, Transfer entity) {
        entity.setMoneyCount(dto.getMoneyCount());
        entity.setCode(dto.getCode());
        entity.setRecipientBank(dto.getRecipientBank());
        entity.setRecipientSurname(dto.getRecipientSurname());
        entity.setRecipientName(dto.getRecipientName());
        entity.setRecipientPatronymic(dto.getRecipientPatronymic());
        entity.setSenderBank(dto.getSenderBank());
        entity.setSenderName(dto.getSenderName());
        entity.setSenderSurname(dto.getSenderSurname());
        entity.setSenderPatronymic(dto.getSenderPatronymic());
        return entity;
    }
}
