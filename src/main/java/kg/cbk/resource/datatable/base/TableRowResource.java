package kg.cbk.resource.datatable.base;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface TableRowResource {

    @JsonProperty("DT_RowId")
    String getDT_RowId();

    @JsonProperty("DT_RowClass")
    String getDT_RowClass();

}
