package it.gov.pagopa.analytics.ingestion.event.dataevents.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import it.gov.pagopa.analytics.ingestion.event.dataevents.enums.DataEventType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "eventType", defaultImpl = DataEventDTO.class, visible = true)
@JsonSubTypes({
  @JsonSubTypes.Type(value = IngestionDataEventDTO.class, name = "INGESTION"),
  @JsonSubTypes.Type(value = ExportDataEventDTO.class, name = "EXPORT_FILE"),
})
public class DataEventDTO <T> {
  private String eventId;
  private String traceId;
  private DataEventType eventType;
  private OffsetDateTime eventDateTime;
  private T payload;
  private String eventDescription;
}

