package it.gov.pagopa.analytics.ingestion.connector.auth;

public interface AuthnService {
    String getAccessToken();
    String getAccessToken(String orgIpaCode);
}
