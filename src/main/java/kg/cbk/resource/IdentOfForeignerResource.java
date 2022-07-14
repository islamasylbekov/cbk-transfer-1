package kg.cbk.resource;

import kg.cbk.resource.datatable.base.BaseResource;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class IdentOfForeignerResource extends BaseResource {

    private LocalDate exitDate;

    private String liberation;

    private LocalDate dateOfRecruitment;

    private String countryOfRecruitment;

    private String countryOfConsignment;

    private String dataOfRecruiters;

    private String countryOfExploitation;
}