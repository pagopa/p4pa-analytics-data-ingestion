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
@Table(name = "export_file")
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = false)
public class ExportFile extends RawBaseEntity implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "export_file_generator")
  @SequenceGenerator(name = "export_file_generator", sequenceName = "export_file_seq", allocationSize = 1)
  private Long exportFilePk;
  @JdbcTypeCode(SqlTypes.JSON)
  private String exportFilePayload;
}
