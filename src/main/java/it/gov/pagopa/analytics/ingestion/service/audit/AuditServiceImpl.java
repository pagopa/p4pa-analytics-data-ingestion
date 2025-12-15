package it.gov.pagopa.analytics.ingestion.service.audit;

import it.gov.pagopa.analytics.ingestion.model.Auth;
import it.gov.pagopa.analytics.ingestion.repository.AuditRepository;
import org.springframework.stereotype.Service;

@Service
public class AuditServiceImpl implements AuditService {
  private final AuditRepository auditRepository;

  public AuditServiceImpl(AuditRepository auditRepository) {
    this.auditRepository = auditRepository;
  }

  @Override
  public void save(String auditDataDTO) {
    auditRepository.save(
      Auth.builder()
        .authPayload(auditDataDTO)
        .build());
  }
}
