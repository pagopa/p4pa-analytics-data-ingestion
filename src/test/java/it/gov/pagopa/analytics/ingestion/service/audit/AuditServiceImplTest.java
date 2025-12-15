package it.gov.pagopa.analytics.ingestion.service.audit;

import it.gov.pagopa.analytics.ingestion.repository.AuditRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AuditServiceImplTest {

  @Mock
  private AuditRepository auditRepositoryMock;

  @InjectMocks
  private AuditServiceImpl auditService;

  @AfterEach
  void verifyNoMoreInteractions(){
    Mockito.verifyNoMoreInteractions(
      auditRepositoryMock
    );
  }

  @Test
  void save() {
    //GIVEN
    String auditDTO = "CEF:0|PiattaformaUnitaria|P4PA-AUTH|1.0|LOGIN_SUCCESS|Authentication success|rt=2025-10-07T07:55:03.793635975Z";

    //WHEN
    auditService.save(auditDTO);

    //THEN
    verify(auditRepositoryMock, times(1)).save(any());
  }
}
