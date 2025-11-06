package it.gov.pagopa.analytics.ingestion.wf.dptypeorg.config;

import it.gov.pagopa.analytics.ingestion.utils.TemporalTestUtils;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

class DebtPositionTypeOrgsIngestionWfConfigTest {

  private final DebtPositionTypeOrgsIngestionWfConfig config = new DebtPositionTypeOrgsIngestionWfConfig();

  private final Map<Class<?>, Class<?>> localActivityInterface2Impl = Map.of();

  @Test
  void testTaskQueueAlignment() throws InvocationTargetException, IllegalAccessException {
    TemporalTestUtils.verifyActivityStubConfiguration(config, localActivityInterface2Impl);
  }
}
