package kg.cbk.resource.datatable.base;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VictimsResource extends BaseResource{

    private String inn;

    private String name;

    private String surname;

    private String patronymic;

    private String dateOfBirth;

    private String gender;

    private String ageOfRegistration;

    private String agenciesOfRegistration;

    private String citizenship;

    private String updatedAt;

    private String idEmployee;

}
