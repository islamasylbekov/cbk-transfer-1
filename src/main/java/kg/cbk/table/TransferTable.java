package kg.cbk.table;

import kg.cbk.entity.Transfer;
import kg.cbk.table.core.AbstractTable;
import kg.cbk.table.core.ActionButton;
import kg.cbk.table.core.Button;
import kg.cbk.table.core.filter.TextFilter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("prototype")
public class TransferTable extends AbstractTable {

    private String url = "/api/transfer/table";

    private String urlFull = url;

    @Override
    protected Class<?> getBaseClass() {
        return Transfer.class;
    }

    @Override
    protected String getUrl() {
        return urlFull;
    }

    @Override
    protected String getTitle() {
        return "transfer";
    }

    @Override
    protected void initButtons() {
        addButton("action.create", "/transfer/create", Button.Color.primary, "user-plus", "_self");
    }

    @Override
    protected void initColumns() {
        addColumn("id", true);
        addColumn("status", true);
        addColumn("senderFullName", true);
        addColumn("recipientFullName", true);
        addColumn("recipientBank", true);
        addColumn("moneyCount", true);

    }

    @Override
    protected void initDropDownButtons() {
        addDropDownButton("table.action-with-selected", "asd", Button.Color.Default,
                List.of(
                        ActionButton.of(
                                ActionButton.ActionButtonType.action,
                                "action.edit",
                                "/transfer/edit",
                                false
                        )
                )
        );
    }

    @Override
    protected void initFilters() {
        setFilters(
                List.of(
                        new TextFilter().setName("senderName").setTitle("domain.senderName"),
                        new TextFilter().setName("senderSurname").setTitle("domain.senderSurname"),
                        new TextFilter().setName("moneyCount").setTitle("domain.moneyCount")
                )
        );
    }

    public TransferTable setCode(String code){
        urlFull = url;
        urlFull = urlFull + "?statusCode=" + code;
        return this;
    }

    public TransferTable build() {
        urlFull = url;
        return this;
    }
}
