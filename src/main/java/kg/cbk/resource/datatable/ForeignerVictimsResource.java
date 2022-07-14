package kg.cbk.resource.datatable;

import kg.cbk.resource.datatable.base.BaseResource;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class ForeignerVictimsResource extends BaseResource {

    private String inn;

    private String name;

    private String surname;

    private String patronymic;

    private LocalDate dateOfBirth;

    private String gender;

    private String ageOfRegistration;

    private String agenciesOfRegistration;

    private String citizenship;

    private String updatedAt;

    private String idEmployee;
}