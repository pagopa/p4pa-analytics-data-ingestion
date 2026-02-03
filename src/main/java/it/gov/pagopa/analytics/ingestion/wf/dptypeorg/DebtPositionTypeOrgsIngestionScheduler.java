package it.gov.pagopa.analytics.ingestion.wf.dptypeorg;

import io.temporal.client.schedules.ScheduleHandle;
import it.gov.pagopa.analytics.ingestion.enums.ScheduleEnum;
import it.gov.pagopa.analytics.ingestion.service.temporal.WorkflowScheduleService;
import it.gov.pagopa.analytics.ingestion.utils.TaskQueueConstants;
import it.gov.pagopa.analytics.ingestion.wf.dptypeorg.wf.DebtPositionTypeOrgsIngestionWF;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Getter
public class DebtPositionTypeOrgsIngestionScheduler {

  private final ScheduleHandle schedule;

  public DebtPositionTypeOrgsIngestionScheduler(
    WorkflowScheduleService workflowScheduleService,
    @Value("${schedule.debt-position-type-orgs-ingestion.cron-expression}") String cronExpression
  ) {
    this.schedule = workflowScheduleService.schedule(
      ScheduleEnum.SCHEDULE_DP_TYPE_ORGS_INGESTION_WF,
      DebtPositionTypeOrgsIngestionWF.class,
      TaskQueueConstants.TASK_QUEUE_DATA_INGESTION,
      cronExpression);
  }

}
