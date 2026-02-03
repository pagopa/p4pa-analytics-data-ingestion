package it.gov.pagopa.analytics.ingestion.connector.auth.config;

import it.gov.pagopa.analytics.ingestion.config.rest.ApiClientConfig;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "rest.auth")
@SuperBuilder
@NoArgsConstructor
public class AuthApiClientConfig extends ApiClientConfig {
}
