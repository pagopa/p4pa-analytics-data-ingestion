package it.gov.pagopa.analytics.ingestion.event.dataevents.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.gov.pagopa.analytics.ingestion.config.json.JsonConfig;
import it.gov.pagopa.analytics.ingestion.event.dataevents.enums.DataEventType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class DataEventDTOTest {

  private final Map<DataEventType, Class<? extends DataEventDTO<?>>> enum2ExpectedModel = Map.ofEntries(
    Map.entry(DataEventType.INGESTION, IngestionDataEventDTO.class),
    Map.entry(DataEventType.EXPORT_FILE, ExportDataEventDTO.class)
  );

  private final ObjectMapper objectMapper = new JsonConfig().objectMapper();

  @Test
  void testExpectedMapIsCompleted() {
    Assertions.assertEquals(
      Arrays.stream(DataEventType.values()).collect(Collectors.toSet()),
      enum2ExpectedModel.keySet()
    );
  }

  @SuppressWarnings("unchecked")
  @Test
  void testSubTypesConfig() {
    JsonSubTypes jsonSubTypes = DataEventDTO.class.getAnnotation(JsonSubTypes.class);
    Assertions.assertNotNull(jsonSubTypes);

    Assertions.assertEquals(enum2ExpectedModel,
      Arrays.stream(jsonSubTypes.value())
        .map(t -> (StringUtils.isNotBlank(t.name()) ? Stream.of(t.name()) : Arrays.stream(t.names()))
          .collect(Collectors.toMap(DataEventType::valueOf,
            n -> (Class<? extends DataEventDTO<?>>)t.value())))
        .reduce(new EnumMap<>(DataEventType.class), (acc, e) -> {
          acc.putAll(e);
          return acc;
        })
    );
  }

  @Test
  void testSerialization() throws JsonProcessingException {
    IngestionDataDTO payload = new IngestionDataDTO();
    payload.setIngestionFlowFileId(1L);

    IngestionDataEventDTO expectedEvent = new IngestionDataEventDTO();
    expectedEvent.setEventId("eventId");
    expectedEvent.setEventType(DataEventType.INGESTION);
    expectedEvent.setPayload(payload);

    DataEventDTO<?> event = DataEventDTO.builder()
      .eventId(expectedEvent.getEventId())
      .eventType(expectedEvent.getEventType())
      .payload(payload)
      .build();

    String serialized = objectMapper.writeValueAsString(event);
    DataEventDTO<?> result = objectMapper.readValue(serialized, DataEventDTO.class);

    Assertions.assertEquals(expectedEvent, result);
  }
}
