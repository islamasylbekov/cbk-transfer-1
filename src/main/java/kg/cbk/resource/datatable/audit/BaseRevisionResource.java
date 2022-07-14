package kg.cbk.resource.datatable.audit;

import kg.cbk.resource.datatable.base.BaseResource;

public class BaseRevisionResource extends BaseResource {

    private String revision;

    private String timestamp;

    private String auditor;

    public String getRevision() {
        return revision;
    }

    public void setRevision(String revision) {
        this.revision = revision;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }
}