package kg.cbk.table.core.filter;

import kg.cbk.component.SelectOption;
import kg.cbk.component.Selectable;
import kg.cbk.table.core.Filter;
import kg.cbk.util.MessageHelper;

import java.util.ArrayList;
import java.util.List;

public class SelectStaticFilter implements Filter {

    private String title;

    private String name;

    private List<Selectable> options;

    public SelectStaticFilter() {
        this.options = new ArrayList<>();
    }

    public SelectStaticFilter addOption(SelectOption option) {
        options.add(option);
        return this;
    }

    public String getTitle() {
        return MessageHelper.translate(title);
    }

    public SelectStaticFilter setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getName() {
        return name;
    }

    public SelectStaticFilter setName(String name) {
        this.name = name;
        return this;
    }

    public List<Selectable> getOptions() {
        return options;
    }

    public SelectStaticFilter setOptions(List<Selectable> options) {
        this.options = options;
        return this;
    }

    @Override
    public String title() {
        return getTitle();
    }

    @Override
    public String name() {
        return getName();
    }

    @Override
    public String node() {
        return "selectStatic";
    }
}
