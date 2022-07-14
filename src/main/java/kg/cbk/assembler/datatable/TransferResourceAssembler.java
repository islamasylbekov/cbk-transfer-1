package kg.cbk.assembler.datatable;

import kg.cbk.assembler.datatable.base.DataTableResourceAssembler;
import kg.cbk.controller.api.TransferControllerApi;
import kg.cbk.entity.Transfer;
import kg.cbk.resource.datatable.TransferResource;
import org.springframework.stereotype.Component;

@Component
public class TransferResourceAssembler extends DataTableResourceAssembler<Transfer, TransferResource> {

    public TransferResourceAssembler() {
        super(TransferControllerApi.class, TransferResource.class);
    }

    @Override
    public TransferResource toResource(Transfer transfer) {
        TransferResource resource = createResourceWithId(transfer.getId(),transfer);
        resource.id = transfer.getId().toString();
        resource.setMoneyCount(transfer.getMoneyCount());
        resource.setRecipientBank(transfer.getRecipientBank() != null ? transfer.getRecipientBank().getSelectorTitle() : "");
        resource.setSenderBank(transfer.getSenderBank() != null ? transfer.getSenderBank().getSelectorTitle() : "");
        resource.setRecipientFullName(transfer.getRecipientName() + " " + transfer.getRecipientSurname());
        resource.setSenderFullName(transfer.getSenderName() + " " + transfer.getSenderSurname());
        resource.setCode(transfer.getCode());
        resource.setEmployee(transfer.getEmployee());
        resource.setStatus(transfer.getStatus().getSelectorTitle());
        return resource;
    }
}
