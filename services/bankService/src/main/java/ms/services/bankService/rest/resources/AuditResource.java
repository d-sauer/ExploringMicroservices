package ms.services.bankService.rest.resources;

import ms.commons.logging.Logger;
import ms.commons.util.ObjectUtils;
import ms.services.bankService.core.audit.model.entities.Audit;
import org.springframework.hateoas.ResourceSupport;

import java.util.Calendar;

/**
 * Created by davor on 28/05/15.
 */
public class AuditResource extends ResourceSupport implements Logger {

    private Calendar start;

    private Calendar end;

    private String type;

    private String data;

    public Calendar getStart() {
        return start;
    }

    public void setStart(Calendar start) {
        this.start = start;
    }

    public Calendar getEnd() {
        return end;
    }

    public void setEnd(Calendar end) {
        this.end = end;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Audit toAudit() {
        Audit audit = new Audit();
        try {
            ObjectUtils.mapValues(this, audit);
        } catch (Exception e) {
            error("Can't map values", e);
        }

        return audit;
    }
}
