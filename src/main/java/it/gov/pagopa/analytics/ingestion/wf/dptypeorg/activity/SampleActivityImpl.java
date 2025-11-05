package it.gov.pagopa.analytics.ingestion.wf.dptypeorg.activity;

import io.temporal.spring.boot.ActivityImpl;
import it.gov.pagopa.analytics.ingestion.connector.debtpositions.DebtPositionTypeOrgService;
import it.gov.pagopa.analytics.ingestion.utils.TaskQueueConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
@Slf4j
@ActivityImpl(taskQueues = TaskQueueConstants.TASK_QUEUE_DATA_INGESTION)
public class SampleActivityImpl implements SampleActivity {

  private final DebtPositionTypeOrgService debtPositionTypeOrgService;

  public SampleActivityImpl(DebtPositionTypeOrgService debtPositionTypeOrgService) {
    this.debtPositionTypeOrgService = debtPositionTypeOrgService;
  }

  @Override
  public String executeSampleActivity() {
    log.info("Executing SAMPLE activity");
    log.info("fetched: {}",
      debtPositionTypeOrgService.getUpdatedDebtPositionTypeOrgs(OffsetDateTime.now().minusDays(7)).size()
    );
    return "OK";
  }
}
