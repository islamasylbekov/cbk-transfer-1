package kg.cbk.table.core.legend;

public class BaseLegend {
    protected String title;

    public BaseLegend() {
    }

    public BaseLegend(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}