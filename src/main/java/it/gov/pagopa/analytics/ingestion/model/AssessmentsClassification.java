package it.gov.pagopa.analytics.ingestion.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import java.io.Serializable;

@Entity
@Table(name = "assessments_classification")
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = false)
public class AssessmentsClassification extends RawBaseEntity implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "assessments_classification_generator")
  @SequenceGenerator(name = "assessments_classification_generator", sequenceName = "assessments_classification_seq", allocationSize = 1)
  private Long assessmentClassificationPk;
  private String assessmentClassificationId;
  private String assessmentClassificationPayload;
}
