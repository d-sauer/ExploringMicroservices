package ms.services.bankService.core.audit.services.impl;

import ms.services.bankService.core.audit.model.entities.Audit;
import ms.services.bankService.core.audit.repositories.AuditRepository;
import ms.services.bankService.core.audit.services.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by davor on 28/05/15.
 */
@Service
public class AuditServiceImpl implements AuditService {

    @Autowired
    private AuditRepository repository;

    @Override
    public List<Audit> findAll() {
        return repository.findAll();
    }
}
