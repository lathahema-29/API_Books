package utils;

import endpoints.Routes;
import io.restassured.http.ContentType;
import payload.ApiClientPayload;

import static io.restassured.RestAssured.given;

public class TokenManager {

    private static String token;

    public static String getToken() {

        if (token == null) {

            ApiClientPayload payload = new ApiClientPayload(
                    FakerUtils.generateName(),
                    FakerUtils.generateEmail()
            );

            token = given()
                    .contentType(ContentType.JSON)
                    .body(payload)
                    .when()
                    .post(Routes.API_CLIENT)
                    .then()
                    .statusCode(201)
                    .extract()
                    .path("accessToken");
        }

        return token;
    }
}