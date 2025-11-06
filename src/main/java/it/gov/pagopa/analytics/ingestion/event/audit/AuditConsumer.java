package it.gov.pagopa.analytics.ingestion.event.audit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;


@Slf4j
@Service
public class AuditConsumer implements Consumer<String> {

  @Override
  public void accept(String auditEvent) {
    log.info("Audit event received: {}", auditEvent);
  }

}
