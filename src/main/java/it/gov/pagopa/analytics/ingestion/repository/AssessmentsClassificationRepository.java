package it.gov.pagopa.analytics.ingestion.repository;

import it.gov.pagopa.analytics.ingestion.model.AssessmentsClassification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssessmentsClassificationRepository extends JpaRepository<AssessmentsClassification, Long> {
}
