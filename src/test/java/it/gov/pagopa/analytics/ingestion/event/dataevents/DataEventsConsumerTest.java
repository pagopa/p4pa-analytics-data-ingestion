package it.gov.pagopa.analytics.ingestion.event.dataevents;

import it.gov.pagopa.analytics.ingestion.event.dataevents.dto.*;
import it.gov.pagopa.analytics.ingestion.repository.AssessmentsClassificationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static it.gov.pagopa.analytics.ingestion.event.dataevents.enums.DataEventType.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DataEventsConsumerTest {

  @Mock
  private AssessmentsClassificationRepository assessmentsClassificationRepository;

  @InjectMocks
  private DataEventsConsumer dataEventsConsumer;

  private final String mockEventId = "event-id-001";

  @Test
  void givenAssessmentsClassificationEventTypeWhenAcceptThenVerify() {
    // GIVEN
    AssessmentEventDTO assessmentData = new AssessmentEventDTO();
    DataEventDTO<AssessmentEventDTO> eventDTO = new AssessmentDataEventDTO();
    eventDTO.setEventId(mockEventId);
    eventDTO.setEventType(ASSESSMENTS_CLASSIFICATION);
    eventDTO.setPayload(assessmentData);

    // WHEN
    dataEventsConsumer.accept(eventDTO);

    // THEN
    verify(assessmentsClassificationRepository, times(1)).save(any());
  }

  @Test
  void givenExportEventTypeWhenAcceptThenVerify() {
    // GIVEN
    ExportDataDTO exportData = new ExportDataDTO();
    DataEventDTO<ExportDataDTO> eventDTO = new ExportDataEventDTO();
    eventDTO.setEventId(mockEventId);
    eventDTO.setEventType(EXPORT_FILE);
    eventDTO.setPayload(exportData);

    // WHEN
    dataEventsConsumer.accept(eventDTO);

    // THEN
    verify(assessmentsClassificationRepository, never()).save(any());
  }

  @Test
  void givenIngestionEventTypeWhenAcceptThenVerify() {
    // GIVEN
    IngestionDataDTO ingestionData = new IngestionDataDTO();
    DataEventDTO<IngestionDataDTO> eventDTO = new IngestionDataEventDTO();
    eventDTO.setEventId(mockEventId);
    eventDTO.setEventType(INGESTION);
    eventDTO.setPayload(ingestionData);

    // WHEN
    dataEventsConsumer.accept(eventDTO);

    // THEN
    verify(assessmentsClassificationRepository, never()).save(any());
  }
}
