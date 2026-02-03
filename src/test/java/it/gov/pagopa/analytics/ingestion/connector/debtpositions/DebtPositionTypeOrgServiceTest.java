package it.gov.pagopa.analytics.ingestion.connector.debtpositions;

import it.gov.pagopa.analytics.ingestion.connector.auth.AuthnService;
import it.gov.pagopa.analytics.ingestion.connector.debtpositions.client.DebtPositionTypeOrgSearchClient;
import it.gov.pagopa.pu.debtpositions.dto.generated.DebtPositionTypeOrg;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DebtPositionTypeOrgServiceTest {

  @Mock
  private AuthnService authnServiceMock;
  @Mock
  private DebtPositionTypeOrgSearchClient searchClientMock;

  private DebtPositionTypeOrgService service;

  @BeforeEach
  void setUp() {
    service = new DebtPositionTypeOrgServiceImpl(authnServiceMock, searchClientMock);
  }

  @AfterEach
  void verifyNoMoreInteractions(){
    Mockito.verifyNoMoreInteractions(authnServiceMock, searchClientMock);
  }

  @Test
  void whenGetDebtPositionTypeOrgCountByOrganizationIdThenInvokeClient() {
    String accessToken = "ACCESSTOKEN";
    OffsetDateTime lastUpdateDate = OffsetDateTime.now();

    List<DebtPositionTypeOrg> expectedResult = List.of();

    when(authnServiceMock.getAccessToken())
      .thenReturn(accessToken);
    when(searchClientMock.getUpdatedDebtPositionTypeOrgs(lastUpdateDate, accessToken))
      .thenReturn(expectedResult);

    List<DebtPositionTypeOrg> result = service.getUpdatedDebtPositionTypeOrgs(lastUpdateDate);

    assertSame(expectedResult, result);
  }
}

