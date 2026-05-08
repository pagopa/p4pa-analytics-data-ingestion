package it.gov.pagopa.analytics.ingestion.wf.dummy.activity;

import io.temporal.spring.boot.ActivityImpl;
import it.gov.pagopa.analytics.ingestion.utils.TaskQueueConstants;
import it.gov.pagopa.analytics.ingestion.wf.dummy.service.DummyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@ActivityImpl(taskQueues = TaskQueueConstants.TASK_QUEUE_DATA_INGESTION)
public class DummyActivityImpl implements DummyActivity {

  private final DummyService dummyService;

  public DummyActivityImpl(DummyService dummyService) {
    this.dummyService = dummyService;
  }

  @Override
  public String executeDummyActivity() {
    log.info("Executing DUMMY activity");
    return dummyService.doSomething();
  }
}
