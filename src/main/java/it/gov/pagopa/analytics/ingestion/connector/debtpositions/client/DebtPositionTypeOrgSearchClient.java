package it.gov.pagopa.analytics.ingestion.connector.debtpositions.client;

import it.gov.pagopa.analytics.ingestion.connector.debtpositions.config.DebtPositionApisHolder;
import it.gov.pagopa.pu.debtpositions.dto.generated.DebtPositionTypeOrg;
import it.gov.pagopa.pu.debtpositions.dto.generated.PagedModelDebtPositionTypeOrgEmbedded;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Slf4j
@Service
public class DebtPositionTypeOrgSearchClient {

  private final DebtPositionApisHolder debtPositionApisHolder;

  public DebtPositionTypeOrgSearchClient(DebtPositionApisHolder debtPositionApisHolder) {
    this.debtPositionApisHolder = debtPositionApisHolder;
  }

  public List<DebtPositionTypeOrg> getUpdatedDebtPositionTypeOrgs(OffsetDateTime lastUpdateDate, String accessToken) {
    PagedModelDebtPositionTypeOrgEmbedded embedded = debtPositionApisHolder.getDebtPositionTypeOrgSearchControllerApi(accessToken)
      .crudDebtPositionTypeOrgsFindByUpdateDateGreaterThanEqualOrderByUpdateDateAsc(
        lastUpdateDate
      ).getEmbedded();
    return embedded!=null
      ? embedded.getDebtPositionTypeOrgs()
      : List.of();
  }

}
