package it.gov.pagopa.analytics.ingestion.repository;

import it.gov.pagopa.analytics.ingestion.model.Ingestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngestionRepository extends JpaRepository<Ingestion, Long> {
}
