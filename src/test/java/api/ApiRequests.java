package api;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class ApiRequests {

    private static final String BASE_URI = "http://company5.qa.internal-services:5008";
    private static final String AUTH_ENDPOINT = "/auth/token";
    private static final String CLIENT_ID = "rUysFIwTCg25LnvUrbwjOvqy43i48F";
    private static final String CLIENT_SECRET = "J9FW8nI$x03JtXcm15YeEcQllsQKl!-!I01jwlDX";

    private Response response;

    /**
     * Get authentication token
     * @return Response object containing token and status
     */
    public Response getAuthToken() {
        String requestBody = "{\n" +
                "  \"client_id\": \"" + CLIENT_ID + "\",\n" +
                "  \"client_secret\": \"" + CLIENT_SECRET + "\"\n" +
                "}";

        response = given()
                .baseUri(BASE_URI)
                .header("Content-Type", "application/json")
                .body(requestBody)
                .log().ifValidationFails()  // Log only if request fails
                .when()
                .post(AUTH_ENDPOINT)
                .then()
                .extract().response();

        return response;
    }

    /**
     * Get status code from the response
     * @return HTTP status code
     */
    public int getStatusCode() {
        if (response == null) {
            throw new IllegalStateException("No response available. Call getAuthToken() first.");
        }
        return response.statusCode();
    }

    /**
     * Extract token from response
     * @return Token string or null if not found
     */
    public String extractToken() {
        if (response == null) {
            throw new IllegalStateException("No response available. Call getAuthToken() first.");
        }

        if (response.statusCode() == 200) {
            return response.jsonPath().getString("token");
        }

        return null;
    }

    /**
     * Check if authentication was successful
     * @return true if status code is 200
     */
    public boolean isAuthSuccessful() {
        return response != null && response.statusCode() == 200;
    }

    /**
     * Get the full response body as string
     * @return Response body
     */
    public String getResponseBody() {
        if (response == null) {
            throw new IllegalStateException("No response available. Call getAuthToken() first.");
        }
        return response.asString();
    }

    /**
     * Print response details for debugging
     */
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
}