package it.gov.pagopa.analytics.ingestion.wf.dptypeorg.config;

import io.temporal.workflow.Workflow;
import it.gov.pagopa.analytics.ingestion.config.temporal.BaseWfConfig;
import it.gov.pagopa.analytics.ingestion.config.temporal.TemporalWFImplementationCustomizer;
import it.gov.pagopa.analytics.ingestion.wf.dptypeorg.activity.DebtPositionTypeOrgSyncActivity;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "workflow.debt-position-type-orgs-ingestion")
public class DebtPositionTypeOrgsIngestionWfConfig extends BaseWfConfig {

  public DebtPositionTypeOrgSyncActivity buildDebtPositionTypeOrgSyncActivityStub() {
    return Workflow.newActivityStub(DebtPositionTypeOrgSyncActivity.class, TemporalWFImplementationCustomizer.baseWfConfig2ActivityOptions(this));
  }
}
