package kg.cbk.resource.datatable;

import kg.cbk.resource.datatable.base.BaseResource;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class BankResource  extends BaseResource {

    private String name;

    private String address;

    private String moneyCount;
}