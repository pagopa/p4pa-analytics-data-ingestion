package it.gov.pagopa.analytics.ingestion.event.dataevents.dto;

import it.gov.pagopa.pu.processexecutions.dto.generated.ExportFile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ExportDataDTO {
  private Long organizationId;
  private Long exportFileId;
  private Long exportedRows;
  private LocalDate exportDate;
  private Long fileSize;
  private ExportFile.ExportFileTypeEnum exportFileType;
}
