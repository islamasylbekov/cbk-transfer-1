package kg.cbk.table.core.filter;

import kg.cbk.table.core.Filter;
import kg.cbk.util.MessageHelper;

public class TextFilter implements Filter {

    public static TextFilter of(String name, String title) {
        return new TextFilter()
                .setName(name)
                .setTitle(title);
    }

    private String title;

    private String name;

    public String getTitle() {
        return MessageHelper.translate(title);
    }

    public TextFilter setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getName() {
        return name;
    }

    public TextFilter setName(String name) {
        this.name = name;
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
        return "text";
    }
}
