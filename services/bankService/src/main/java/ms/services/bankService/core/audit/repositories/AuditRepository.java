package ms.services.bankService.core.audit.repositories;

import ms.services.bankService.core.audit.model.entities.Audit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by davor on 25/05/15.
 */
@Repository
public interface AuditRepository extends JpaRepository<Audit, Long> {
}
