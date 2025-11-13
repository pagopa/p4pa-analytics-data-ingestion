package it.gov.pagopa.analytics.ingestion.wf.dptypeorg.activity;

import io.temporal.spring.boot.ActivityImpl;
import it.gov.pagopa.analytics.ingestion.connector.debtpositions.DebtPositionTypeOrgService;
import it.gov.pagopa.analytics.ingestion.mapper.DebtPositionTypeOrgMapper;
import it.gov.pagopa.analytics.ingestion.repository.DebtPositionTypeOrgRepository;
import it.gov.pagopa.analytics.ingestion.utils.TaskQueueConstants;
import it.gov.pagopa.analytics.ingestion.utils.Utilities;
import it.gov.pagopa.pu.debtpositions.dto.generated.DebtPositionTypeOrg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.List;

@Service
@Slf4j
@ActivityImpl(taskQueues = TaskQueueConstants.TASK_QUEUE_DATA_INGESTION)
public class DebtPositionTypeOrgSyncActivityImpl implements DebtPositionTypeOrgSyncActivity {

  private final DebtPositionTypeOrgService debtPositionTypeOrgService;
  private final DebtPositionTypeOrgRepository debtPositionTypeOrgRepository;
  private final DebtPositionTypeOrgMapper debtPositionTypeOrgMapper;


  public DebtPositionTypeOrgSyncActivityImpl(
    DebtPositionTypeOrgService debtPositionTypeOrgService,
    DebtPositionTypeOrgRepository debtPositionTypeOrgRepository,
    DebtPositionTypeOrgMapper debtPositionTypeOrgMapper) {
    this.debtPositionTypeOrgService = debtPositionTypeOrgService;
    this.debtPositionTypeOrgRepository = debtPositionTypeOrgRepository;
    this.debtPositionTypeOrgMapper = debtPositionTypeOrgMapper;
  }

  @Override
  public Integer syncDebtPositionTypeOrg() {
    log.info("Starting synchronization of DebtPositionTypeOrg");
    OffsetDateTime lastProcessedUpdateDate = Utilities.localDateTimeToOffsetDateTime(
      debtPositionTypeOrgRepository.findMaxUpdateDate()
    );
    log.info("Fetched last updateDate for stored DebtPositionTypeOrg: {}", lastProcessedUpdateDate);
    List<DebtPositionTypeOrg> updatedDebtPositionTypeOrgs = debtPositionTypeOrgService
      .getUpdatedDebtPositionTypeOrgs(
        lastProcessedUpdateDate == null ? Utilities.getEpochOffsetDateTime() : lastProcessedUpdateDate
      );
    int batchSizeOfStoredEntities = debtPositionTypeOrgRepository.saveAll(
      updatedDebtPositionTypeOrgs.stream()
        .map(debtPositionTypeOrgMapper::mapDebtPositionTypeOrg)
        .toList()
    ).size();
    log.info("Batch size of stored DebtPositionTypeOrg entities: {}", batchSizeOfStoredEntities);
    return batchSizeOfStoredEntities;
  }
}
