package kg.cbk.resource.datatable.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.experimental.SuperBuilder;
import org.springframework.hateoas.ResourceSupport;

import java.io.Serializable;

public class BaseResource extends ResourceSupport implements TableRowResource, Serializable {

    @JsonProperty
    public String id;

    public String uuid;

    public String rowClass;

    public String getUuid() {
        return uuid;
    }

    public BaseResource setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public void setRowClass(String rowClass) {
        this.rowClass = rowClass;
    }

    public String getRowClass() {
        return rowClass;
    }

    @Override
    public String getDT_RowId() {
        return String.format("row_%s", id);
    }

    @Override
    public String getDT_RowClass() {
        return rowClass;
    }

}
