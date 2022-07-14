package kg.cbk.table.core.filter;

import kg.cbk.table.core.Filter;
import kg.cbk.util.MessageHelper;

/**
 * Date: 14.04.17
 * Time: 12:51
 *
 */
public class CheckboxFilter implements Filter {

    private static final long serialVersionUID = 5153831182405569148L;

    private String title;

    private String name;

    @Override
    public String title() {
        return MessageHelper.translate(title);
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String node() {
        return "checkbox";
    }

    public String getTitle() {
        return title;
    }

    public CheckboxFilter setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getName() {
        return name;
    }

    public CheckboxFilter setName(String name) {
        this.name = name;
        return this;
    }
}
