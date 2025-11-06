
package it.gov.pagopa.analytics.ingestion.wf.dptypeorg.wf;

import io.temporal.spring.boot.WorkflowImpl;
import it.gov.pagopa.analytics.ingestion.config.temporal.TemporalWFImplementationCustomizer;
import it.gov.pagopa.analytics.ingestion.utils.TaskQueueConstants;
import it.gov.pagopa.analytics.ingestion.wf.dptypeorg.activity.SampleActivity;
import it.gov.pagopa.analytics.ingestion.wf.dptypeorg.config.DebtPositionTypeOrgsIngestionWfConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

@Slf4j
@WorkflowImpl(taskQueues = TaskQueueConstants.TASK_QUEUE_DATA_INGESTION)
public class DebtPositionTypeOrgsIngestionWFImpl implements DebtPositionTypeOrgsIngestionWF, ApplicationContextAware {

  private SampleActivity sampleActivity;


  /**
   * Temporal workflow will not allow to use injection in order to avoid <a href="https://docs.temporal.io/workflows#non-deterministic-change">non-deterministic changes</a> due to dynamic reconfiguration.<BR />
   * Anyway it allows to override ActivityOptions, but actually it's not supporting the override based on the particular workflow.<BR />
   * In {@link TemporalWFImplementationCustomizer} we are already setting defaults to all workflows.<BR />
   * Use this as an example to override based on the particular workflow.
   */

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    DebtPositionTypeOrgsIngestionWfConfig wfConfig = applicationContext.getBean(DebtPositionTypeOrgsIngestionWfConfig.class);
    sampleActivity = wfConfig.buildSampleActivityStub();

  }

  @Override
  public String ingestDebtPositionTypeOrgs() {
    log.info("Executing AssessmentsClassifications DataMart process WF");
    String result = sampleActivity.executeSampleActivity();
    log.info("AssessmentsClassifications process WF completed: {}", result);
    return result;
  }
}

