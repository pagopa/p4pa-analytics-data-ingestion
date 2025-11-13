package it.gov.pagopa.analytics.ingestion.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import it.gov.pagopa.analytics.ingestion.event.dataevents.dto.AssessmentEventDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;

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
  private Long assessmentClassificationPk;
  private String assessmentClassificationId;

  @JdbcTypeCode(SqlTypes.JSON)
  private AssessmentEventDTO assessmentClassificationPayload;
}
