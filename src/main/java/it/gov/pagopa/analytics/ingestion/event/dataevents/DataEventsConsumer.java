package it.gov.pagopa.analytics.ingestion.event.dataevents;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.gov.pagopa.analytics.ingestion.event.dataevents.dto.*;
import it.gov.pagopa.analytics.ingestion.exception.custom.WorkflowInternalErrorException;
import it.gov.pagopa.analytics.ingestion.model.AssessmentsClassification;
import it.gov.pagopa.analytics.ingestion.repository.AssessmentsClassificationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

import static it.gov.pagopa.analytics.ingestion.event.dataevents.enums.DataEventType.*;


@Slf4j
@Service
public class DataEventsConsumer implements Consumer<String> {

  private final AssessmentsClassificationRepository assessmentsClassificationRepository;
  private final ObjectMapper objectMapper;

  public DataEventsConsumer(AssessmentsClassificationRepository assessmentsClassificationRepository, ObjectMapper objectMapper) {
    this.assessmentsClassificationRepository = assessmentsClassificationRepository;
    this.objectMapper = objectMapper;
  }

  @Override
  public void accept(String rawJsonEvent) {
    try {
      DataEventDTO<?> paymentEventDTO = objectMapper.readValue(rawJsonEvent, DataEventDTO.class);
      if (INGESTION.equals(paymentEventDTO.getEventType())) {
        log.info("DataEvent received: {} occurred on IngestionFlowFile having eventId {}", paymentEventDTO.getEventType(), paymentEventDTO.getEventId());
      } else if (EXPORT_FILE.equals(paymentEventDTO.getEventType())) {
        log.info("DataEvent received: {} occurred on ExportFile having eventId {}", paymentEventDTO.getEventType(), paymentEventDTO.getEventId());
      } else if (ASSESSMENTS_CLASSIFICATION.equals(paymentEventDTO.getEventType())) {
        log.info("DataEvent received: {} occurred on AssessmentsClassification having eventId {}", paymentEventDTO.getEventType(), paymentEventDTO.getEventId());
        saveAssessmentsClassificationEvent(paymentEventDTO.getEventId(), rawJsonEvent);
      } else {
        log.info("DataEvent skipped: {}", paymentEventDTO.getEventType());
      }
    } catch (JsonProcessingException e) {
      throw new WorkflowInternalErrorException("DataEvent deserialization error: "+e.getMessage());
    }
  }

  private void saveAssessmentsClassificationEvent(String eventId, String assessmentDataDTO) {
    assessmentsClassificationRepository.save(
      AssessmentsClassification.builder()
      .assessmentClassificationId(eventId)
      .assessmentClassificationPayload(assessmentDataDTO)
      .build());
  }

}
