package it.gov.pagopa.analytics.ingestion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.webmvc.autoconfigure.error.ErrorMvcAutoConfiguration;

@SpringBootApplication(exclude = {ErrorMvcAutoConfiguration.class})
public class AnalyticsDataIngestionApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnalyticsDataIngestionApplication.class, args);
	}

}
