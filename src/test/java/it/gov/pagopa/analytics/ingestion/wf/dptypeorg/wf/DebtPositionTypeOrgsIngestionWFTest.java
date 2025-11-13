package it.gov.pagopa.analytics.ingestion.wf.dptypeorg.wf;

import it.gov.pagopa.analytics.ingestion.wf.dptypeorg.activity.DebtPositionTypeOrgSyncActivity;
import it.gov.pagopa.analytics.ingestion.wf.dptypeorg.config.DebtPositionTypeOrgsIngestionWfConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationContext;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DebtPositionTypeOrgsIngestionWFTest {

  @Mock
  private DebtPositionTypeOrgSyncActivity debtPositionTypeOrgSyncActivityMock;

  private DebtPositionTypeOrgsIngestionWFImpl wf;

  @BeforeEach
  void setUp() {
    DebtPositionTypeOrgsIngestionWfConfig debtPositionTypeOrgsIngestionWfConfigMock = mock(DebtPositionTypeOrgsIngestionWfConfig.class);
    ApplicationContext applicationContextMock = mock(ApplicationContext.class);
    when(debtPositionTypeOrgsIngestionWfConfigMock.buildDebtPositionTypeOrgSyncActivityStub()).thenReturn(debtPositionTypeOrgSyncActivityMock);

    when(applicationContextMock.getBean(DebtPositionTypeOrgsIngestionWfConfig.class)).thenReturn(debtPositionTypeOrgsIngestionWfConfigMock);

    wf = new DebtPositionTypeOrgsIngestionWFImpl();
    wf.setApplicationContext(applicationContextMock);
  }

  @AfterEach
  void verifyNoMoreInteractions() {
    Mockito.verifyNoMoreInteractions(
      debtPositionTypeOrgSyncActivityMock
    );
  }
  @Test
  void givenSuccessfulSyncWhenIngestDebtPositionTypeOrgsThenLogSynchronizedTaxonomies() {
    // Given
    Integer expectedResult = 1;
    when(debtPositionTypeOrgSyncActivityMock.syncDebtPositionTypeOrg()).thenReturn(expectedResult);

    // When
    Integer actualResult = wf.ingestDebtPositionTypeOrgs();

    // Then
    Assertions.assertSame(expectedResult, actualResult);
  }
}
