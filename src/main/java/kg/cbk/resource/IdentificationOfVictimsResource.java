package kg.cbk.resource;

import kg.cbk.resource.datatable.base.BaseResource;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IdentificationOfVictimsResource extends BaseResource {

    private String exitDate;

    private String liberation;

    private String dateOfRecruitment;

    private String countryOfRecruitment;

    private String countryOfConsignment;

    private String dataOfRecruiters;

    private String countryOfExploitation;
}
