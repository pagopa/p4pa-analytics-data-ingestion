package it.gov.pagopa.analytics.ingestion;

import it.gov.pagopa.analytics.ingestion.utils.Constants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.webmvc.autoconfigure.error.ErrorMvcAutoConfiguration;

import java.util.TimeZone;

@SpringBootApplication(
  exclude = {ErrorMvcAutoConfiguration.class},
  scanBasePackages = {
    "it.gov.pagopa.pu",
    "it.gov.pagopa.analytics"
  }
)
public class AnalyticsDataIngestionApplication {

	public static void main(String[] args) {
    TimeZone.setDefault(Constants.DEFAULT_TIMEZONE);
		SpringApplication.run(AnalyticsDataIngestionApplication.class, args);
	}

}
