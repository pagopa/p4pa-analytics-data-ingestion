package it.gov.pagopa.analytics.ingestion.repository;

import it.gov.pagopa.analytics.ingestion.model.ExportFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExportFileRepository extends JpaRepository<ExportFile, Long> {
}
