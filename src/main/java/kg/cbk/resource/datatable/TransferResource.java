package kg.cbk.resource.datatable;

import kg.cbk.entity.Employee;
import kg.cbk.resource.datatable.base.BaseResource;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferResource extends BaseResource {

    private String senderFullName;

    private String senderBank;

    private String recipientFullName;

    private String recipientBank;

    private String code;

    private Long moneyCount;

    private Employee employee;

    private String status;
}
