package it.gov.pagopa.analytics.ingestion.event.dataevents.dto;

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
public class DataEventDTO {
  private String eventId;
  private String traceId;
  private DataEventType eventType;
  private OffsetDateTime eventDateTime;
  private String eventDescription;
}

