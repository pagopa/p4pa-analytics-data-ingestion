package it.gov.pagopa.analytics.ingestion.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;

@Entity
@Table(name = "ingestion")
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = false)
public class Ingestion extends RawBaseEntity implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ingestion_generator")
  @SequenceGenerator(name = "ingestion_generator", sequenceName = "ingestion_seq", allocationSize = 1)
  private Long ingestionPk;
  @JdbcTypeCode(SqlTypes.JSON)
  private String ingestionPayload;
}
