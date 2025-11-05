package it.gov.pagopa.analytics.ingestion.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ScheduleEnum {
  SCHEDULE_DP_TYPE_ORGS_INGESTION_WF("AssessmentsClassificationsProcessSchedule");

  private final String value;
}

