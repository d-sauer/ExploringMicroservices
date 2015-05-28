package ms.services.bankService.rest.resources;

import org.springframework.hateoas.ResourceSupport;

import java.util.List;

/**
 * Created by davor on 28/05/15.
 */
public class AuditListResource extends ResourceSupport {

    private List<AuditResource> audits;

    public List<AuditResource> getAudits() {
        return audits;
    }

    public void setAudits(List<AuditResource> audits) {
        this.audits = audits;
    }
}
