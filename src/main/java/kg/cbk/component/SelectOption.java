package kg.cbk.component;

public class SelectOption implements Selectable {

    public String id;
    public String title;

    public SelectOption() {
    }

    public SelectOption(String id, String title) {
        this.id = id;
        this.title = title;
    }

    @Override
    public String getSelectorId() {
        return id;
    }

    @Override
    public String getSelectorTitle() {
        return title;
    }
}