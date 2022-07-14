package kg.cbk.component;

public interface Selectable {
    String getSelectorId();

    String getSelectorTitle();

    default String getClassName(){
        return "";
    }
}
