package kg.cbk.table.core.filter;

import kg.cbk.table.core.Filter;
import kg.cbk.util.MessageHelper;

/**
 * Date: 15.04.17
 * Time: 23:15
 *
 */
public class MonthFilter implements Filter {

    private static final long serialVersionUID = -3586305336491974109L;

    private String title;

    private String name;

    public String getTitle() {
        return title;
    }

    public MonthFilter setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getName() {
        return name;
    }

    public MonthFilter setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String title() {
        return MessageHelper.translate(getTitle());
    }

    @Override
    public String name() {
        return getName();
    }

    @Override
    public String node() {
        return "month";
    }
}

