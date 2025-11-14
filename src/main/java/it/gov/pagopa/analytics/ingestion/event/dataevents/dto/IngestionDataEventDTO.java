package it.gov.pagopa.analytics.ingestion.event.dataevents.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public class IngestionDataEventDTO extends DataEventDTO{
}
