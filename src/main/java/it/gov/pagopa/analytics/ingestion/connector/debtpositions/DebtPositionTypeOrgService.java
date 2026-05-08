package it.gov.pagopa.analytics.ingestion.connector.debtpositions;

import it.gov.pagopa.pu.debtpositions.dto.generated.DebtPositionTypeOrg;

import java.time.OffsetDateTime;
import java.util.List;

public interface DebtPositionTypeOrgService {
  List<DebtPositionTypeOrg> getUpdatedDebtPositionTypeOrgs(OffsetDateTime lastUpdateDate);
}
