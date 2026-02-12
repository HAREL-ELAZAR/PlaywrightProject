package tests.apiTests;

import api.services.AuthApi;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AuthApiTest {

    private AuthApi authApi;

    @BeforeClass
    public void setup() {
        authApi = new AuthApi();
    }

    @Test(priority = 1, description = "Verify successful authentication and status code 200")
    public void testAuthTokenStatusCode() {
        Response response = authApi.getAuthToken();

        Assert.assertEquals(response.statusCode(), 200, "Status code should be 200");
        System.out.println("✓ Status Code: " + response.statusCode());
    }

    @Test(priority = 2, description = "Verify token is present in response")
    public void testTokenIsPresent() {
        Response response = authApi.getAuthToken();

        String token = response.jsonPath().getString("token");
        Assert.assertNotNull(token, "Token should not be null");
        Assert.assertFalse(token.isBlank(), "Token should not be empty");

        System.out.println("✓ Token received: " + token.substring(0, Math.min(20, token.length())) + "...");
    }

    @Test(priority = 3, description = "Verify authentication success by checking token and status")
    public void testAuthenticationSuccess() {
        Response response = authApi.getAuthToken();

        Assert.assertEquals(response.statusCode(), 200, "Authentication should return 200");
        Assert.assertNotNull(response.jsonPath().getString("token"), "Token must exist when auth succeeds");

        System.out.println("✓ Authentication successful");
    }

    @Test(priority = 4, description = "Verify response time is acceptable")
    public void testResponseTime() {
        Response response = authApi.getAuthToken();

        long responseTime = response.time();
        Assert.assertTrue(responseTime < 3000, "Response time should be less than 3 seconds");

        System.out.println("✓ Response time: " + responseTime + "ms");
    }

    @Test(priority = 5, description = "Print full response details (for debugging)")
    public void testPrintResponseDetails() {
        Response response = authApi.getAuthToken();

        System.out.println("=== Response Details ===");
        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Status Line: " + response.statusLine());
        System.out.println("Response Time: " + response.time() + "ms");
        System.out.println("Response Body: " + response.asString());
        System.out.println("=======================");
    }

    @Test(priority = 6, description = "Verify response contains expected fields")
    public void testResponseStructure() {
        Response response = authApi.getAuthToken();

        Assert.assertTrue(response.asString().contains("token"),
                "Response should contain 'token' field");
        System.out.println("✓ Response structure is valid");
    }
}
