package kg.cbk.table.core.legend;

public class IconLegend extends BaseLegend {
    private String icon;

    public IconLegend(String title, String icon) {
        super(title);
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}