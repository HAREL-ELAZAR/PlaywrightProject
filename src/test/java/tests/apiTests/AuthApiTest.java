package tests.apiTests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AuthApiTest {

    private api.ApiRequests ApiRequests;

    @BeforeClass
    public void setup() {
        ApiRequests = new api.ApiRequests();
    }

    @Test(priority = 1, description = "Verify successful authentication and status code 200")
    public void testAuthTokenStatusCode() {
        Response response = ApiRequests.getAuthToken();
        int statusCode = response.statusCode();

        Assert.assertEquals(statusCode, 200, "Status code should be 200");
        System.out.println("✓ Status Code: " + statusCode);
    }

    @Test(priority = 2, description = "Verify token is present in response")
    public void testTokenIsPresent() {
        ApiRequests.getAuthToken();
        String token = ApiRequests.extractToken();

        Assert.assertNotNull(token, "Token should not be null");
        Assert.assertFalse(token.isEmpty(), "Token should not be empty");
        System.out.println("✓ Token received: " + token.substring(0, Math.min(20, token.length())) + "...");
    }

    @Test(priority = 3, description = "Verify authentication success")
    public void testAuthenticationSuccess() {
        ApiRequests.getAuthToken();
        boolean isSuccessful = ApiRequests.isAuthSuccessful();

        Assert.assertTrue(isSuccessful, "Authentication should be successful");
        System.out.println("✓ Authentication successful");
    }

    @Test(priority = 4, description = "Verify response time is acceptable")
    public void testResponseTime() {
        Response response = ApiRequests.getAuthToken();
        long responseTime = response.time();

        Assert.assertTrue(responseTime < 3000, "Response time should be less than 3 seconds");
        System.out.println("✓ Response time: " + responseTime + "ms");
    }

    @Test(priority = 5, description = "Print full response details")
    public void testPrintResponseDetails() {
        ApiRequests.getAuthToken();
        ApiRequests.printResponseDetails();
    }

    @Test(priority = 6, description = "Verify response contains expected fields")
    public void testResponseStructure() {
        Response response = ApiRequests.getAuthToken();

        // Verify response has required fields (adjust based on actual response)
        Assert.assertTrue(response.asString().contains("token"),
                "Response should contain 'token' field");
        System.out.println("✓ Response structure is valid");
    }
}