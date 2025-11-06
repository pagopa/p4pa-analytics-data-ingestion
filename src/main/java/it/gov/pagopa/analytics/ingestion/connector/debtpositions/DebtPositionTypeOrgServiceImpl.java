package it.gov.pagopa.analytics.ingestion.connector.debtpositions;

import it.gov.pagopa.analytics.ingestion.connector.auth.AuthnService;
import it.gov.pagopa.analytics.ingestion.connector.debtpositions.client.DebtPositionTypeOrgSearchClient;
import it.gov.pagopa.pu.debtpositions.dto.generated.DebtPositionTypeOrg;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class DebtPositionTypeOrgServiceImpl implements DebtPositionTypeOrgService {

  private final AuthnService authnService;
  private final DebtPositionTypeOrgSearchClient searchClient;

  public DebtPositionTypeOrgServiceImpl(AuthnService authnService, DebtPositionTypeOrgSearchClient searchClient) {
    this.authnService = authnService;
    this.searchClient = searchClient;
  }

  @Override
  public List<DebtPositionTypeOrg> getUpdatedDebtPositionTypeOrgs(OffsetDateTime lastUpdateDate) {
    return searchClient.getUpdatedDebtPositionTypeOrgs(lastUpdateDate, authnService.getAccessToken());
  }
}
