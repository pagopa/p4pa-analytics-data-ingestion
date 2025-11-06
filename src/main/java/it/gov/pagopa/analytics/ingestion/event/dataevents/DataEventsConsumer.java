package it.gov.pagopa.analytics.ingestion.event.dataevents;

import it.gov.pagopa.analytics.ingestion.event.dataevents.dto.DataEventDTO;
import it.gov.pagopa.analytics.ingestion.event.dataevents.dto.ExportDataDTO;
import it.gov.pagopa.analytics.ingestion.event.dataevents.dto.IngestionDataDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;


@Slf4j
@Service
public class DataEventsConsumer implements Consumer<DataEventDTO<?>> {

  @Override
  public void accept(DataEventDTO paymentEventDTO) {

    if (paymentEventDTO.getPayload() instanceof IngestionDataDTO ingestionData) {
      log.info("DataEvent received: {} occurred on IngestionFlowFile {}", paymentEventDTO.getEventType(), ingestionData.getIngestionFlowFileId());
    } else if (paymentEventDTO.getPayload() instanceof ExportDataDTO exportData) {
      log.info("DataEvent received: {} occurred on ExportFile {}", paymentEventDTO.getEventType(), exportData.getExportFileId());
    } else {
      log.info("DataEvent skipped: {}", paymentEventDTO.getEventType());
    }
  }

}
