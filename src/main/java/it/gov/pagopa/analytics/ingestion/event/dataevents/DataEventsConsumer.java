package it.gov.pagopa.analytics.ingestion.event.dataevents;

import it.gov.pagopa.analytics.ingestion.event.dataevents.dto.AssessmentEventDTO;
import it.gov.pagopa.analytics.ingestion.event.dataevents.dto.DataEventDTO;
import it.gov.pagopa.analytics.ingestion.event.dataevents.dto.ExportDataDTO;
import it.gov.pagopa.analytics.ingestion.event.dataevents.dto.IngestionDataDTO;
import it.gov.pagopa.analytics.ingestion.model.AssessmentsClassification;
import it.gov.pagopa.analytics.ingestion.repository.AssessmentsClassificationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;


@Slf4j
@Service
public class DataEventsConsumer implements Consumer<DataEventDTO<?>> {

  private final AssessmentsClassificationRepository assessmentsClassificationRepository;

  public DataEventsConsumer(AssessmentsClassificationRepository assessmentsClassificationRepository) {
    this.assessmentsClassificationRepository = assessmentsClassificationRepository;
  }

  @Override
  public void accept(DataEventDTO paymentEventDTO) {

    switch (paymentEventDTO.getPayload()) {
      case IngestionDataDTO ingestionData ->
        log.info("DataEvent received: {} occurred on IngestionFlowFile {}", paymentEventDTO.getEventType(), ingestionData.getIngestionFlowFileId());
      case ExportDataDTO exportData ->
        log.info("DataEvent received: {} occurred on ExportFile {}", paymentEventDTO.getEventType(), exportData.getExportFileId());
      case AssessmentEventDTO assessmentEventDTO -> {
        log.info("DataEvent received: {} occurred on AssessmentsClassification {}", paymentEventDTO.getEventType(), assessmentEventDTO.getAssessmentId());
        saveAssessmentsClassificationEvent(paymentEventDTO.getEventId(), assessmentEventDTO);
      }
      case null, default ->
        log.info("DataEvent skipped: {}", paymentEventDTO.getEventType());
    }
  }

  private void saveAssessmentsClassificationEvent(String eventId, AssessmentEventDTO assessmentEventDTO) {
    assessmentsClassificationRepository.save(
      AssessmentsClassification.builder()
      .assessmentClassificationId(eventId)
      .assessmentClassificationPayload(assessmentEventDTO)
      .build());
  }

}
