package kg.cbk.table.reference;

import kg.cbk.entity.reference.CommonReference;
import kg.cbk.table.core.AbstractTable;
import org.springframework.stereotype.Component;

@Component
public class CommonReferenceTable extends AbstractTable {
    private static final long serialVersionUID = 9057742360512974188L;

    @Override
    protected Class<?> getBaseClass() {
        return CommonReference.class;
    }

    @Override
    protected String getUrl() {
        return "/api/common-reference/table";
    }

    @Override
    protected String getTitle() {
        return "commonReference.table";
    }

    @Override
    protected void initFilters() {
        addFilterSelect("type", "/reference/common-reference-type");
    }

    public Object build() {
        return null;
    }

    @Override
    protected void initColumns() {
        addColumn("id", true);
        addColumn("type", true);
        addColumn("code", true);
        addColumn("title", true);
    }
}