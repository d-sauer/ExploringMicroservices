package ms.services.bankService.core.audit.services;

import ms.services.bankService.core.audit.model.entities.Audit;

import java.util.List;

/**
 * Created by davor on 28/05/15.
 */
public interface AuditService {

    List<Audit> findAll();

}
