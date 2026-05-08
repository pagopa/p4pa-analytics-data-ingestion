package it.gov.pagopa.analytics.ingestion.mapper;

import it.gov.pagopa.analytics.ingestion.utils.Utilities;
import org.springframework.stereotype.Service;

@Service
public class DebtPositionTypeOrgMapper {

  public it.gov.pagopa.analytics.ingestion.model.DebtPositionTypeOrg mapDebtPositionTypeOrg(
    it.gov.pagopa.pu.debtpositions.dto.generated.DebtPositionTypeOrg other) {
    return it.gov.pagopa.analytics.ingestion.model.DebtPositionTypeOrg.builder()
      .debtPositionTypeOrgId(other.getDebtPositionTypeOrgId())
      .debtPositionTypeId(other.getDebtPositionTypeId())
      .organizationId(other.getOrganizationId())
      .balance(other.getBalance())
      .code(other.getCode())
      .description(other.getDescription())
      .iban(other.getIban())
      .postalIban(other.getPostalIban())
      .postalAccountCode(other.getPostalAccountCode())
      .holderPostalCc(other.getHolderPostalCc())
      .orgSector(other.getOrgSector())
      .amountCents(other.getAmountCents())
      .externalPaymentUrl(other.getExternalPaymentUrl())
      .flagAnonymousFiscalCode(Boolean.TRUE.equals(other.getFlagAnonymousFiscalCode()))
      .flagMandatoryDueDate(Boolean.TRUE.equals(other.getFlagMandatoryDueDate()))
      .flagSpontaneous(Boolean.TRUE.equals(other.getFlagSpontaneous()))
      .flagNotifyIo(Boolean.TRUE.equals(other.getFlagNotifyIo()))
      .serviceId(other.getServiceId())
      .ioTemplateSubject(other.getIoTemplateSubject())
      .ioTemplateMessage(other.getIoTemplateMessage())
      .flagActive(Boolean.TRUE.equals(other.getFlagActive()))
      .flagNotifyOutcomePush(Boolean.TRUE.equals(other.getFlagNotifyOutcomePush()))
      .notifyOutcomePushOrgSilServiceId(other.getNotifyOutcomePushOrgSilServiceId())
      .flagAmountActualization(Boolean.TRUE.equals(other.getFlagAmountActualization()))
      .amountActualizationOrgSilServiceId(other.getAmountActualizationOrgSilServiceId())
      .flagExternal(Boolean.TRUE.equals(other.getFlagExternal()))
      .spontaneousFormId(other.getSpontaneousFormId())
      .creationDate(Utilities.offsetDateTimeToLocalDateTime(other.getCreationDate()))
      .updateDate(Utilities.offsetDateTimeToLocalDateTime(other.getUpdateDate()))
      .updateOperatorExternalId(other.getUpdateOperatorExternalId())
      .updateTraceId(other.getUpdateTraceId())
      .build();
  }

}
