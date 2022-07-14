package kg.cbk.table.employee;

import kg.cbk.entity.Employee;
import kg.cbk.table.core.AbstractTable;
import kg.cbk.table.core.ActionButton;
import kg.cbk.table.core.Button;
import kg.cbk.table.core.filter.TextFilter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeTable extends AbstractTable {

    @Override
    protected Class<?> getBaseClass() {
        return Employee.class;
    }

    @Override
    protected String getUrl() {
        return "/api/employee/table";
    }

    @Override
    protected String getTitle() {
        return "employee";
    }

    @Override
    protected void initButtons() {
        addButton("action.create", "/employee/create", Button.Color.primary, "user-plus", "_self");
    }

    @Override
    protected void initColumns() {
        addColumn("id", true);
        addColumn("surname", true);
        addColumn("name", true);
        addColumn("login", true);
        addColumn("rank", true);
        addColumn("role", true);
        addColumn("agency", true);
    }

    @Override
    protected void initDropDownButtons() {
        addDropDownButton("table.action-with-selected", "asd", Button.Color.Default,
                List.of(
                        ActionButton.of(
                                ActionButton.ActionButtonType.action,
                                "employee.change-password",
                                "/employee/change-password",
                                false
                        ),
                        ActionButton.of(
                                ActionButton.ActionButtonType.action,
                                "action.edit",
                                "/employee/edit",
                                false
                        )
                )
        );
    }

    @Override
    protected void initFilters() {
        setFilters(
                List.of(
                        new TextFilter().setName("login").setTitle("employee.login"),
                        new TextFilter().setName("surname").setTitle("employee.surname"),
                        new TextFilter().setName("name").setTitle("employee.name")
                )
        );
    }

    public EmployeeTable build() {
        return this;
    }

}