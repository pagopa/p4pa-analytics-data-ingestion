package it.gov.pagopa.analytics.ingestion.event.audit;

import it.gov.pagopa.analytics.ingestion.model.Auth;
import it.gov.pagopa.analytics.ingestion.repository.AuditRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;


@Slf4j
@Service
public class AuditConsumer implements Consumer<String> {

  private final AuditRepository auditRepository;

  public AuditConsumer(AuditRepository auditRepository) {
    this.auditRepository = auditRepository;
  }

  @Override
  public void accept(String auditEvent) {
    log.info("Audit event received: {}", auditEvent);
    saveAuditEvent(auditEvent);
  }

  private void saveAuditEvent(String auditDataDTO) {
    auditRepository.save(
      Auth.builder()
        .authPayload(auditDataDTO)
        .build());
  }
}
