package it.gov.pagopa.analytics.ingestion.wf.dptypeorg.activity;

import it.gov.pagopa.analytics.ingestion.connector.debtpositions.DebtPositionTypeOrgService;
import it.gov.pagopa.analytics.ingestion.mapper.DebtPositionTypeOrgMapper;
import it.gov.pagopa.analytics.ingestion.repository.DebtPositionTypeOrgRepository;
import it.gov.pagopa.analytics.ingestion.utils.Utilities;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@ExtendWith(MockitoExtension.class)
class DebtPositionTypeOrgSyncActivityImplTest {

  @Mock
  private DebtPositionTypeOrgService debtPositionTypeOrgServiceMock;
  @Mock
  private DebtPositionTypeOrgRepository debtPositionTypeOrgRepositoryMock;
  @Mock
  private DebtPositionTypeOrgMapper debtPositionTypeOrgMapperMock;
  @InjectMocks
  private DebtPositionTypeOrgSyncActivityImpl activity;

  @AfterEach
  void verifyNoMoreInteractions() {
    Mockito.verifyNoMoreInteractions(
      debtPositionTypeOrgServiceMock,
      debtPositionTypeOrgRepositoryMock,
      debtPositionTypeOrgMapperMock
    );
  }

  @Test
  void givenNotNullLastDateWhenSyncDebtPositionTypeOrgThenSuccess() {
    //GIVEN
    OffsetDateTime offsetDateTime = OffsetDateTime.now();
    Mockito
      .when(debtPositionTypeOrgRepositoryMock.findMaxUpdateDate())
      .thenReturn(offsetDateTime.toLocalDateTime());
    Mockito
      .when(debtPositionTypeOrgServiceMock.getUpdatedDebtPositionTypeOrgs(offsetDateTime))
      .thenReturn(
        List.of(
          buildPUDebtPositionTypeOrg(1L),
          buildPUDebtPositionTypeOrg(2L)
        )
      );
    Mockito
      .when(debtPositionTypeOrgMapperMock.mapDebtPositionTypeOrg(ArgumentMatchers.any()))
      .thenReturn(null);
    Mockito
      .when(debtPositionTypeOrgRepositoryMock.saveAll(ArgumentMatchers.anyCollection()))
      .thenReturn(buildAnalyticsDebtPositionTypeOrgList(2));
    int expectedInsertCount = 2;
    //WHEN
    Integer actualInsertCount = activity.syncDebtPositionTypeOrg();
    //THEN
    Assertions.assertEquals(expectedInsertCount, actualInsertCount);
    Mockito.verify(debtPositionTypeOrgMapperMock, Mockito.times(2)).mapDebtPositionTypeOrg(ArgumentMatchers.any());
  }

  @Test
  void givenNullLastDateWhenSyncDebtPositionTypeOrgThenSuccess() {
    //GIVEN
    Mockito
      .when(debtPositionTypeOrgRepositoryMock.findMaxUpdateDate())
      .thenReturn(null);
    Mockito
      .when(debtPositionTypeOrgServiceMock.getUpdatedDebtPositionTypeOrgs(Utilities.getEpochOffsetDateTime()))
      .thenReturn(
        List.of(
          buildPUDebtPositionTypeOrg(1L),
          buildPUDebtPositionTypeOrg(2L),
          buildPUDebtPositionTypeOrg(3L)
        )
      );
    Mockito
      .when(debtPositionTypeOrgMapperMock.mapDebtPositionTypeOrg(ArgumentMatchers.any()))
      .thenReturn(null);
    Mockito
      .when(debtPositionTypeOrgRepositoryMock.saveAll(ArgumentMatchers.anyCollection()))
      .thenReturn(buildAnalyticsDebtPositionTypeOrgList(3));
    int expectedInsertCount = 3;
    //WHEN
    Integer actualInsertCount = activity.syncDebtPositionTypeOrg();
    //THEN
    Assertions.assertEquals(expectedInsertCount, actualInsertCount);
    Mockito.verify(debtPositionTypeOrgMapperMock, Mockito.times(3)).mapDebtPositionTypeOrg(ArgumentMatchers.any());
  }

  private static List<it.gov.pagopa.analytics.ingestion.model.DebtPositionTypeOrg> buildAnalyticsDebtPositionTypeOrgList(int num) {
    return IntStream.range(0, num)
      .mapToObj(i -> it.gov.pagopa.analytics.ingestion.model.DebtPositionTypeOrg.builder().build())
      .collect(Collectors.toList());
  }

  private static it.gov.pagopa.pu.debtpositions.dto.generated.DebtPositionTypeOrg buildPUDebtPositionTypeOrg(Long debtPositionTypeOrgId) {
    return it.gov.pagopa.pu.debtpositions.dto.generated.DebtPositionTypeOrg.builder()
      .debtPositionTypeOrgId(debtPositionTypeOrgId)
      .debtPositionTypeId(1L)
      .organizationId(1L)
      .code("code")
      .description("description")
      .build();
  }

}
