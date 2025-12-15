package it.gov.pagopa.analytics.ingestion.repository;

import it.gov.pagopa.analytics.ingestion.model.Auth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditRepository extends JpaRepository<Auth, Long> {
}
