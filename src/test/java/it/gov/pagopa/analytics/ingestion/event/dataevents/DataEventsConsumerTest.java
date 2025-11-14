package it.gov.pagopa.analytics.ingestion.event.dataevents;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.gov.pagopa.analytics.ingestion.event.dataevents.dto.*;
import it.gov.pagopa.analytics.ingestion.exception.custom.WorkflowInternalErrorException;
import it.gov.pagopa.analytics.ingestion.repository.AssessmentsClassificationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static it.gov.pagopa.analytics.ingestion.event.dataevents.enums.DataEventType.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DataEventsConsumerTest {

  @Mock
  private AssessmentsClassificationRepository assessmentsClassificationRepository;
  @Mock
  private ObjectMapper objectMapper;

  @InjectMocks
  private DataEventsConsumer dataEventsConsumer;

  private final String expectedJsonPayload = "{\"eventId\":\"test-event-123\",\"eventType\":\"ASSESSMENTS_CLASSIFICATION\",\"payload\":{\"assessmentId\":\"3324\"}}";

  @Test
  void givenAssessmentsClassificationEventTypeWhenAcceptThenVerify() throws JsonProcessingException {
    // GIVEN
    DataEventDTO eventDTO = new DataEventDTO();
    eventDTO.setEventType(ASSESSMENTS_CLASSIFICATION);
    eventDTO.setEventId("test-event-123");

    when(objectMapper.readValue(expectedJsonPayload, DataEventDTO.class)).thenReturn(eventDTO);
    // WHEN
    dataEventsConsumer.accept(expectedJsonPayload);

    // THEN
    verify(assessmentsClassificationRepository, times(1)).save(any());
  }

  @Test
  void givenExportEventTypeWhenAcceptThenVerify() throws JsonProcessingException {
    // GIVEN
    DataEventDTO eventDTO = new DataEventDTO();
    eventDTO.setEventType(EXPORT_FILE);
    eventDTO.setEventId("test-event-123");

    when(objectMapper.readValue(expectedJsonPayload, DataEventDTO.class)).thenReturn(eventDTO);

    // WHEN
    dataEventsConsumer.accept(expectedJsonPayload);

    // THEN
    verify(assessmentsClassificationRepository, never()).save(any());
  }

  @Test
  void givenIngestionEventTypeWhenAcceptThenVerify() throws JsonProcessingException {
    // GIVEN
    DataEventDTO eventDTO = new DataEventDTO();
    eventDTO.setEventType(INGESTION);
    eventDTO.setEventId("test-event-123");

    when(objectMapper.readValue(expectedJsonPayload, DataEventDTO.class)).thenReturn(eventDTO);

    // WHEN
    dataEventsConsumer.accept(expectedJsonPayload);

    // THEN
    verify(assessmentsClassificationRepository, never()).save(any());
  }

  @Test
  void givenMalformedJsonWhenAcceptThenThrowWorkflowInternalErrorException() throws JsonProcessingException {
    // GIVEN
    String malformedJson = "{ \"malformed\": json, }";

    when(objectMapper.readValue(malformedJson, DataEventDTO.class))
      .thenThrow(new JsonProcessingException("Simulated JSON parsing error") {});

    // WHEN / THEN
    assertThrows(WorkflowInternalErrorException.class, () -> dataEventsConsumer.accept(malformedJson));

    verify(assessmentsClassificationRepository, never()).save(any());
  }
}
