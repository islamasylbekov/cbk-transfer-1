package kg.cbk.table.core;

public class Order {

    private Integer column;

    private String dir;

    public Integer getColumn() {
        return column;
    }

    public Order setColumn(Integer column) {
        this.column = column;
        return this;
    }

    public String getDir() {
        return dir;
    }

    public Order setDir(String dir) {
        this.dir = dir;
        return this;
    }
}
