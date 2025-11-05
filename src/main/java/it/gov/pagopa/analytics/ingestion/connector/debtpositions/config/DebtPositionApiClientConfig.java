package it.gov.pagopa.analytics.ingestion.connector.debtpositions.config;

import it.gov.pagopa.analytics.ingestion.config.rest.ApiClientConfig;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "rest.debt-positions")
@SuperBuilder
@NoArgsConstructor
public class DebtPositionApiClientConfig extends ApiClientConfig {
}
