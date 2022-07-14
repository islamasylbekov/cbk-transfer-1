package kg.cbk.resource.datatable;

import kg.cbk.resource.datatable.base.BaseResource;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class EmployeeResource extends BaseResource {

    private String inn;

    private String surname;

    private String name;

    private String login;

    private String role;

    private Set<String> roles;

    private String rank;

    private String agency;

}
