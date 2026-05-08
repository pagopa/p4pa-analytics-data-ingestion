package it.gov.pagopa.analytics.ingestion.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "debt_position_type_orgs")
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = false)
public class DebtPositionTypeOrg extends RawBaseEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "debt_position_type_orgs_generator")
  @SequenceGenerator(name = "debt_position_type_orgs_generator", sequenceName = "debt_position_type_orgs_seq", allocationSize = 1)
  private Long debtPositionTypeOrgPk;
  private Long debtPositionTypeOrgId;
  private Long debtPositionTypeId;
  private Long organizationId;
  private String balance;
  private String code;
  private String description;
  private String iban;
  private String postalIban;
  private String postalAccountCode;
  private String holderPostalCc;
  private String orgSector;
  private Long amountCents;
  private String externalPaymentUrl;
  private boolean flagAnonymousFiscalCode;
  private boolean flagMandatoryDueDate;
  private boolean flagSpontaneous;
  private boolean flagNotifyIo;
  private String serviceId;
  private String ioTemplateSubject;
  private String ioTemplateMessage;
  private boolean flagActive;
  private boolean flagNotifyOutcomePush;
  private Long notifyOutcomePushOrgSilServiceId;
  private boolean flagAmountActualization;
  private Long amountActualizationOrgSilServiceId;
  private boolean flagExternal;
  private Long spontaneousFormId;

  private LocalDateTime creationDate;
  private LocalDateTime updateDate;
  private String updateOperatorExternalId;
  private String updateTraceId;

}
