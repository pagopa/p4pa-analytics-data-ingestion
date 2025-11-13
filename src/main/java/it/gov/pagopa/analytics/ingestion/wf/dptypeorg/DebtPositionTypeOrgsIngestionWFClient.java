package it.gov.pagopa.analytics.ingestion.wf.dptypeorg;

import it.gov.pagopa.analytics.ingestion.dto.generated.WorkflowCreatedDTO;
import it.gov.pagopa.analytics.ingestion.service.temporal.WorkflowClientService;
import it.gov.pagopa.analytics.ingestion.service.temporal.WorkflowService;
import it.gov.pagopa.analytics.ingestion.utils.TaskQueueConstants;
import it.gov.pagopa.analytics.ingestion.wf.dptypeorg.wf.DebtPositionTypeOrgsIngestionWF;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static it.gov.pagopa.analytics.ingestion.utils.Utilities.generateWorkflowId;

@Slf4j
@Service
public class DebtPositionTypeOrgsIngestionWFClient {

  private final WorkflowService workflowService;
  private final WorkflowClientService workflowClientService;
  private static final String ON_DEMAND = "ON-DEMAND";

  public DebtPositionTypeOrgsIngestionWFClient(WorkflowService workflowService, WorkflowClientService workflowClientService) {
    this.workflowService = workflowService;
    this.workflowClientService = workflowClientService;
  }

  public WorkflowCreatedDTO ingestDebtPositionTypeOrgs() {
    log.info("Starting ingestion of DebtPositionTypeOrgs {}", ON_DEMAND);
    String taskQueue = TaskQueueConstants.TASK_QUEUE_DATA_INGESTION;
    String workflowId = generateWorkflowId(ON_DEMAND, DebtPositionTypeOrgsIngestionWF.class);

    DebtPositionTypeOrgsIngestionWF workflow = workflowService.buildWorkflowStub(
      DebtPositionTypeOrgsIngestionWF.class,
      taskQueue,
      workflowId
    );

    return workflowClientService.start(workflow::ingestDebtPositionTypeOrgs);
  }
}
