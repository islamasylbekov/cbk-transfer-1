package kg.cbk.controller.web.crud;


import javax.validation.constraints.NotNull;

public interface CrudController {

    @NotNull
    String templates();

    @NotNull
    String url();

}
