package api.client;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Http_Requests {

    private final String baseUri;

    public Http_Requests(String baseUri) {
        this.baseUri = baseUri;
    }

    private Response sendRequest(String method, String path, String token, Object body) {

        var request = given()
                .baseUri(baseUri)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .log().ifValidationFails();

        if (token != null && !token.isBlank()) {
            request.header("Authorization", "Bearer " + token);
        }

        if (body != null) {
            request.body(body);
        }

        return switch (method.toUpperCase()) {
            case "GET" -> request.when().get(path).then().extract().response();
            case "POST" -> request.when().post(path).then().extract().response();
            case "PUT" -> request.when().put(path).then().extract().response();
            case "DELETE" -> request.when().delete(path).then().extract().response();
            default -> throw new IllegalArgumentException("Unsupported HTTP method: " + method);
        };
    }

    // ---- Short wrappers ----
    public Response get(String path, String token) {
        return sendRequest("GET", path, token, null);
    }

    public Response post(String path, String token, Object body) {
        return sendRequest("POST", path, token, body);
    }

    public Response put(String path, String token, Object body) {
        return sendRequest("PUT", path, token, body);
    }

    public Response delete(String path, String token) {
        return sendRequest("DELETE", path, token, null);
    }
}
