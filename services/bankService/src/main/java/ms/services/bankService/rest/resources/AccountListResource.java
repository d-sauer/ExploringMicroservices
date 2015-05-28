package ms.services.bankService.rest.resources;

import org.springframework.hateoas.ResourceSupport;

import java.util.List;

/**
 * Created by davor on 28/05/15.
 */
public class AccountListResource extends ResourceSupport {

    private List<AccountResource> resources;

    public List<AccountResource> getResources() {
        return resources;
    }

    public void setResources(List<AccountResource> resources) {
        this.resources = resources;
    }
}
