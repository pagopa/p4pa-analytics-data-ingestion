package it.gov.pagopa.analytics.ingestion.wf.dptypeorg.activity;

import io.temporal.spring.boot.ActivityImpl;
import it.gov.pagopa.analytics.ingestion.utils.TaskQueueConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@ActivityImpl(taskQueues = TaskQueueConstants.TASK_QUEUE_DATA_INGESTION)
public class SampleActivityImpl implements SampleActivity {

  @Override
  public String executeSampleActivity() {
    log.info("Executing SAMPLE activity");
    return "OK";
  }
}
