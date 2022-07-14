package kg.cbk.table.core.filter;

import kg.cbk.component.SelectOption;
import kg.cbk.component.Selectable;
import kg.cbk.table.core.Filter;
import kg.cbk.util.MessageHelper;

import java.util.ArrayList;
import java.util.List;

public class SelectStaticMultipleFilter implements Filter {

    private String title;

    private String name;

    private List<Selectable> options;

    public SelectStaticMultipleFilter() {
        this.options = new ArrayList<>();
    }

    public SelectStaticMultipleFilter addOption(SelectOption option) {
        options.add(option);
        return this;
    }

    public String getTitle() {
        return MessageHelper.translate(title);
    }

    public SelectStaticMultipleFilter setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getName() {
        return name;
    }

    public SelectStaticMultipleFilter setName(String name) {
        this.name = name;
        return this;
    }

    public List<Selectable> getOptions() {
        return options;
    }

    public SelectStaticMultipleFilter setOptions(List<Selectable> options) {
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
        return "selectStaticMultiple";
    }
}
