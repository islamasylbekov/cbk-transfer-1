package kg.cbk.table;

import kg.cbk.entity.Bank;
import kg.cbk.entity.Employee;
import kg.cbk.table.core.AbstractTable;
import kg.cbk.table.core.ActionButton;
import kg.cbk.table.core.Button;
import kg.cbk.table.core.filter.TextFilter;
import kg.cbk.table.employee.EmployeeTable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BankTable extends AbstractTable {

        @Override
        protected Class<?> getBaseClass() {
            return Bank.class;
        }

        @Override
        protected String getUrl() {
            return "/api/bank/table";
        }

        @Override
        protected String getTitle() {
            return "bank";
        }

        @Override
        protected void initButtons() {
            addButton("action.create", "/bank/create", Button.Color.primary, "user-plus", "_self");
        }

        @Override
        protected void initColumns() {
            addColumn("id", true);
            addColumn("name", true);
            addColumn("address", true);
            addColumn("moneyCount", true);

        }

        @Override
        protected void initDropDownButtons() {
            addDropDownButton("table.action-with-selected", "asd", Button.Color.Default,
                    List.of(
                            ActionButton.of(
                                    ActionButton.ActionButtonType.action,
                                    "action.edit",
                                    "/bank/edit",
                                    false
                            )
                    )
            );
        }

        @Override
        protected void initFilters() {
            setFilters(
                    List.of(
                            new TextFilter().setName("name").setTitle("bank.name"),
                            new TextFilter().setName("address").setTitle("bank.address"),
                            new TextFilter().setName("moneyCount").setTitle("bank.moneyCount")
                    )
            );
        }

        public BankTable build() {
            return this;
        }
}
