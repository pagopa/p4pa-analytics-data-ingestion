package it.gov.pagopa.analytics.ingestion.mapper;

import it.gov.pagopa.analytics.ingestion.utils.TestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;

class DebtPositionTypeOrgMapperTest {

  DebtPositionTypeOrgMapper mapper = new DebtPositionTypeOrgMapper();

  @Test
  void mapDebtPositionTypeOrg() {
    //GIVEN
    OffsetDateTime creationDate = OffsetDateTime.now();
    OffsetDateTime updateDate = OffsetDateTime.now();

    it.gov.pagopa.pu.debtpositions.dto.generated.DebtPositionTypeOrg debtPositionTypeOrg =
      it.gov.pagopa.pu.debtpositions.dto.generated.DebtPositionTypeOrg.builder()
      .debtPositionTypeOrgId(1L)
      .debtPositionTypeId(1L)
      .organizationId(1L)
      .balance("balance")
      .code("code")
      .description("desciption")
      .iban("iban")
      .postalIban("postalIban")
      .postalAccountCode("postalAccountCode")
      .holderPostalCc("holderPostalCc")
      .orgSector("orgSector")
      .xsdDefinitionRef("xsdDefinitionRef")
      .amountCents(1234L)
      .externalPaymentUrl("externalPaymentUrl")
      .flagAnonymousFiscalCode(true)
      .flagMandatoryDueDate(true)
      .flagSpontaneous(true)
      .flagNotifyIo(true)
      .serviceId("serviceId")
      .ioTemplateSubject("ioTemplateSubject")
      .ioTemplateMessage("ioTemplateMessage")
      .flagActive(true)
      .flagNotifyOutcomePush(true)
      .notifyOutcomePushOrgSilServiceId(1L)
      .flagAmountActualization(true)
      .amountActualizationOrgSilServiceId(1234L)
      .flagExternal(true)
      .spontaneousFormId(1L)
      .creationDate(creationDate)
      .updateDate(updateDate)
      .updateTraceId("updateTraceId")
      .updateOperatorExternalId("updateOperatorExternalId")
      .build();

    it.gov.pagopa.analytics.ingestion.model.DebtPositionTypeOrg expectedResult =
      it.gov.pagopa.analytics.ingestion.model.DebtPositionTypeOrg.builder()
        .debtPositionTypeOrgId(1L)
        .debtPositionTypeId(1L)
        .organizationId(1L)
        .balance("balance")
        .code("code")
        .description("desciption")
        .iban("iban")
        .postalIban("postalIban")
        .postalAccountCode("postalAccountCode")
        .holderPostalCc("holderPostalCc")
        .orgSector("orgSector")
        .xsdDefinitionRef("xsdDefinitionRef")
        .amountCents(1234L)
        .externalPaymentUrl("externalPaymentUrl")
        .flagAnonymousFiscalCode(true)
        .flagMandatoryDueDate(true)
        .flagSpontaneous(true)
        .flagNotifyIo(true)
        .serviceId("serviceId")
        .ioTemplateSubject("ioTemplateSubject")
        .ioTemplateMessage("ioTemplateMessage")
        .flagActive(true)
        .flagNotifyOutcomePush(true)
        .notifyOutcomePushOrgSilServiceId(1L)
        .flagAmountActualization(true)
        .amountActualizationOrgSilServiceId(1234L)
        .flagExternal(true)
        .spontaneousFormId(1L)
        .creationDate(creationDate.toLocalDateTime())
        .updateDate(updateDate.toLocalDateTime())
        .updateTraceId("updateTraceId")
        .updateOperatorExternalId("updateOperatorExternalId")
        .build();
    //WHEN
    it.gov.pagopa.analytics.ingestion.model.DebtPositionTypeOrg actualResult = mapper.mapDebtPositionTypeOrg(debtPositionTypeOrg);
    //THEN
    Assertions.assertEquals(expectedResult, actualResult);
    TestUtils.checkNotNullFields(actualResult, "debtPositionTypeOrgPk", "processedTime");
  }

}
