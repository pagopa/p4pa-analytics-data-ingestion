package it.gov.pagopa.analytics.ingestion.event.audit;

import it.gov.pagopa.analytics.ingestion.service.audit.AuditService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AuditConsumerTest {

  @Mock
  private AuditService auditServiceMock;

  @InjectMocks
  private AuditConsumer auditConsumer;

  @AfterEach
  void verifyNoMoreInteractions(){
    Mockito.verifyNoMoreInteractions(
      auditServiceMock
    );
  }

  @Test
  void givenAuditEventTypeWhenAcceptThenVerify() {
    //GIVEN
    String auditDTO = "CEF:0|PiattaformaUnitaria|P4PA-AUTH|1.0|LOGIN_SUCCESS|Authentication success|rt=2025-10-07T07:55:03.793635975Z";

    // WHEN
    auditConsumer.accept(auditDTO);

    // THEN
    verify(auditServiceMock, times(1)).save(auditDTO);
  }
}
