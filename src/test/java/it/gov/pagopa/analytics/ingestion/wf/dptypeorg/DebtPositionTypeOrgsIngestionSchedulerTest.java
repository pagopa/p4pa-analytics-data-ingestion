package it.gov.pagopa.analytics.ingestion.wf.dptypeorg;

import io.temporal.client.schedules.ScheduleHandle;
import it.gov.pagopa.analytics.ingestion.enums.ScheduleEnum;
import it.gov.pagopa.analytics.ingestion.service.temporal.WorkflowScheduleService;
import it.gov.pagopa.analytics.ingestion.utils.TaskQueueConstants;
import it.gov.pagopa.analytics.ingestion.utils.TemporalTestUtils;
import it.gov.pagopa.analytics.ingestion.wf.dptypeorg.wf.DebtPositionTypeOrgsIngestionWF;
import it.gov.pagopa.analytics.ingestion.wf.dptypeorg.wf.DebtPositionTypeOrgsIngestionWFImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DebtPositionTypeOrgsIngestionSchedulerTest {

  @Mock
  private WorkflowScheduleService workflowScheduleServiceMock;

  @AfterEach
  void verifyNoMoreInteractions(){
    Mockito.verifyNoMoreInteractions(workflowScheduleServiceMock);
  }

  @Test
  void givenServiceCreationThenInvokeSchedule(){
    // Given
    String cronExpression = "cron";

    ScheduleHandle expectedResult = Mockito.mock(ScheduleHandle.class);
    String taskQueue = TaskQueueConstants.TASK_QUEUE_DATA_INGESTION;
    Mockito.when(workflowScheduleServiceMock.schedule(
        ScheduleEnum.SCHEDULE_DP_TYPE_ORGS_INGESTION_WF,
        DebtPositionTypeOrgsIngestionWF.class,
        taskQueue,
        cronExpression
      ))
      .thenReturn(expectedResult);

    // When
    DebtPositionTypeOrgsIngestionScheduler scheduler = new DebtPositionTypeOrgsIngestionScheduler(workflowScheduleServiceMock, cronExpression);
    ScheduleHandle result = scheduler.getSchedule();

    // Then
    Assertions.assertSame(expectedResult, result);

    TemporalTestUtils.verifyWorkflowTaskQueueConfiguration(taskQueue, DebtPositionTypeOrgsIngestionWFImpl.class);
  }
}
