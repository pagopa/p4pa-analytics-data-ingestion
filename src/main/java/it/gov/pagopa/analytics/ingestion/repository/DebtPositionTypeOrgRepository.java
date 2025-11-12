package it.gov.pagopa.analytics.ingestion.repository;

import it.gov.pagopa.analytics.ingestion.model.DebtPositionTypeOrg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface DebtPositionTypeOrgRepository extends JpaRepository<DebtPositionTypeOrg, Long> {
  @Query("select max(dpto.updateDate) from DebtPositionTypeOrg as dpto")
  LocalDateTime findMaxUpdateDate();
}
