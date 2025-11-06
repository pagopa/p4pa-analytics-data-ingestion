package it.gov.pagopa.analytics.ingestion.wf.dptypeorg;

import it.gov.pagopa.analytics.ingestion.dto.generated.WorkflowCreatedDTO;
import it.gov.pagopa.analytics.ingestion.service.temporal.WorkflowClientService;
import it.gov.pagopa.analytics.ingestion.service.temporal.WorkflowService;
import it.gov.pagopa.analytics.ingestion.utils.TaskQueueConstants;
import it.gov.pagopa.analytics.ingestion.utils.TemporalTestUtils;
import it.gov.pagopa.analytics.ingestion.wf.dptypeorg.wf.DebtPositionTypeOrgsIngestionWF;
import it.gov.pagopa.analytics.ingestion.wf.dptypeorg.wf.DebtPositionTypeOrgsIngestionWFImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DebtPositionTypeOrgsIngestionWFClientTest {

  @Mock
  private WorkflowService workflowServiceMock;
  @Mock
  private WorkflowClientService workflowClientServiceMock;
  @Mock
  private DebtPositionTypeOrgsIngestionWF wfMock;

  private DebtPositionTypeOrgsIngestionWFClient client;

  @BeforeEach
  void init() {
    client = new DebtPositionTypeOrgsIngestionWFClient(workflowServiceMock, workflowClientServiceMock);
  }

  @AfterEach
  void verifyNoMoreInteractions() {
    Mockito.verifyNoMoreInteractions(workflowServiceMock, workflowClientServiceMock);
  }

  @Test
  void whenIngestDebtPositionTypeOrgsThenOk() {
    // Given
    String taskQueue = TaskQueueConstants.TASK_QUEUE_DATA_INGESTION;
    WorkflowCreatedDTO expectedResult = new WorkflowCreatedDTO("DebtPositionTypeOrgsIngestionWF-ON-DEMAND", "RUNID");

    Mockito.when(workflowServiceMock.buildWorkflowStub(DebtPositionTypeOrgsIngestionWF.class, taskQueue, expectedResult.getWorkflowId()))
      .thenReturn(wfMock);

    TemporalTestUtils.configureWorkflowClientServiceMock(workflowClientServiceMock, expectedResult);

    // When
    WorkflowCreatedDTO result = client.ingestDebtPositionTypeOrgs();

    // Then
    Assertions.assertEquals(expectedResult, result);
    Mockito.verify(wfMock).ingestDebtPositionTypeOrgs();

    TemporalTestUtils.verifyWorkflowTaskQueueConfiguration(taskQueue, DebtPositionTypeOrgsIngestionWFImpl.class);
  }
}
