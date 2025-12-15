package it.gov.pagopa.analytics.ingestion.event.audit;

import it.gov.pagopa.analytics.ingestion.service.audit.AuditService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Slf4j
@Service
public class AuditConsumer implements Consumer<String> {

  private final AuditService auditService;

  public AuditConsumer(AuditService auditService) {
    this.auditService = auditService;
  }

  @Override
  public void accept(String auditEvent) {
    log.info("Audit event received: {}", auditEvent);
    saveAuditEvent(auditEvent);
  }

  private void saveAuditEvent(String auditDataDTO) {
    auditService.save(auditDataDTO);
  }
}
