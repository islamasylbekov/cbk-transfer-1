package kg.cbk.assembler.datatable;

import kg.cbk.assembler.datatable.base.DataTableResourceAssembler;
import kg.cbk.controller.api.BankApiController;
import kg.cbk.controller.web.BankController;
import kg.cbk.entity.Bank;
import kg.cbk.resource.datatable.BankResource;
import org.springframework.stereotype.Component;

@Component
public class BankResourceAssembler  extends DataTableResourceAssembler<Bank, BankResource> {

    public BankResourceAssembler() {
        super(BankApiController.class, BankResource.class);
    }

    @Override
    public BankResource toResource(Bank bank) {
        BankResource resource = createResourceWithId(bank.getId(), bank);
        resource.id = bank.getSelectorId();
        resource.setName(bank.getName());
        resource.setAddress(bank.getAddress());
        resource.setMoneyCount(bank.getMoneyCount().toString());
        return resource;
    }
}