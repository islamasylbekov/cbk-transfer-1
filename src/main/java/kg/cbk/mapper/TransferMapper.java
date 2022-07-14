package kg.cbk.mapper;


import kg.cbk.entity.Transfer;
import kg.cbk.models.TransferDto;

public interface TransferMapper {

    Transfer entityToDto(TransferDto dto);

    TransferDto dtoToEntity(Transfer entity);

    Transfer dtoToEntity(TransferDto dto, Transfer entity);
}
