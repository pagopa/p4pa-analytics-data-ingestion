package it.gov.pagopa.analytics.ingestion.wf.dptypeorg.activity;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface SampleActivity {

  @ActivityMethod
  String executeSampleActivity();
}
