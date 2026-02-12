package api.services;

import api.client.Http_Requests;
import io.restassured.response.Response;
import utilities.ConfigManager;

public class AuthApi {

    private final Http_Requests http;
    private Response response;

    public AuthApi() {
        this.http = new Http_Requests(ConfigManager.getRequired("base.uri"));
    }

    // =========================
    // AUTH
    // =========================
    public Response getAuthToken() {
        String clientId = ConfigManager.getRequired("client.id");
        String clientSecret = ConfigManager.getRequired("client.secret");

        String body = """
                {
                  "client_id": "%s",
                  "client_secret": "%s"
                }
                """.formatted(clientId, clientSecret);

        response = http.post("/auth/token", null, body);
        return response;
    }

    // =========================
    // Generic GET usage
    // =========================
    public Response get(String path, String token) {
        response = http.get(path, token);
        return response;
    }

    // =========================
    // Response Helpers
    // =========================
    public int getStatusCode() {
        validateResponse();
        return response.statusCode();
    }

    public String extractToken() {
        validateResponse();
        if (response.statusCode() == 200) {
            return response.jsonPath().getString("token");
        }
        return null;
    }

    public boolean isAuthSuccessful() {
        return response != null && response.statusCode() == 200;
    }

    public String getResponseBody() {
        validateResponse();
        return response.asString();
    }

    public void printResponseDetails() {
        if (response == null) {
            System.out.println("No response available");
            return;
        }

        System.out.println("=== Response Details ===");
        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Status Line: " + response.statusLine());
        System.out.println("Response Time: " + response.time() + "ms");
        System.out.println("Response Body: " + response.asString());
        System.out.println("=======================");
    }

    private void validateResponse() {
        if (response == null) {
            throw new IllegalStateException("No response available. Execute a request first.");
        }
    }
}
