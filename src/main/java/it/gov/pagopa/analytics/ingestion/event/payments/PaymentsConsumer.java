package it.gov.pagopa.analytics.ingestion.event.payments;

import it.gov.pagopa.analytics.ingestion.event.payments.dto.PaymentEventDTO;
import it.gov.pagopa.pu.debtpositions.dto.generated.DebtPositionDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;


@Slf4j
@Service
public class PaymentsConsumer implements Consumer<PaymentEventDTO<?>> {

  @Override
  public void accept(PaymentEventDTO paymentEventDTO) {

    if (paymentEventDTO.getPayload() instanceof DebtPositionDTO debtPosition) {
      log.info("PaymentsEvent received: {} occurred on DebtPosition {}", paymentEventDTO.getEventType(), debtPosition.getDebtPositionId());
    } else {
      log.info("PaymentsEvent skipped: {}", paymentEventDTO.getEventType());
    }
  }

}
