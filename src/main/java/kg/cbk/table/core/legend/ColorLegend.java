package kg.cbk.table.core.legend;

public class ColorLegend extends BaseLegend {
    private String color;

    public ColorLegend(String title, String color) {
        super(title);
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}