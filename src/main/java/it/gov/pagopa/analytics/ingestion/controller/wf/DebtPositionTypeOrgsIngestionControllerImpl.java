package it.gov.pagopa.analytics.ingestion.controller.wf;

import it.gov.pagopa.analytics.ingestion.controller.generated.DebtPositionTypeOrgsIngestionApi;
import it.gov.pagopa.analytics.ingestion.dto.generated.WorkflowCreatedDTO;
import it.gov.pagopa.analytics.ingestion.wf.dptypeorg.DebtPositionTypeOrgsIngestionWFClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class DebtPositionTypeOrgsIngestionControllerImpl implements DebtPositionTypeOrgsIngestionApi {

  private final DebtPositionTypeOrgsIngestionWFClient wfClient;

  public DebtPositionTypeOrgsIngestionControllerImpl(DebtPositionTypeOrgsIngestionWFClient wfClient) {
    this.wfClient = wfClient;
  }

  @Override
  public ResponseEntity<WorkflowCreatedDTO> ingestDebtPositionTypeOrgs() {
    log.info("Requested execution of DebtPositionTypeOrgsIngestionWF workflow");
    return ResponseEntity.status(HttpStatus.CREATED)
      .body(wfClient.ingestDebtPositionTypeOrgs());
  }
}
