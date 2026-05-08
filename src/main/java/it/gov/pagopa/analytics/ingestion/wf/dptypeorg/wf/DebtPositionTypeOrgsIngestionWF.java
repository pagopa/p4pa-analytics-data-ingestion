
package it.gov.pagopa.analytics.ingestion.wf.dptypeorg.wf;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;


/**
 * Workflow to ingest DebtPositionTypeOrgs
 */
@WorkflowInterface
public interface DebtPositionTypeOrgsIngestionWF {
  @WorkflowMethod
  Integer ingestDebtPositionTypeOrgs();
}
