package tests;

import base.BaseTest;
import endpoints.Routes;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;
import payload.OrderPayload;
import utils.TokenManager;

import static io.restassured.RestAssured.given;

public class SimpleBooksTest extends BaseTest {

    String orderId;

    @Test(priority = 1)
    public void checkAPIStatus() {

        String status =
                given()
                        .when()
                        .get(Routes.STATUS)
                        .then()
                        .statusCode(200)
                        .extract()
                        .path("status");

        Assert.assertEquals(status, "OK");
    }

    @Test(priority = 2)
    public void getAllBooks() {

        given()
                .queryParam("limit", 10)
                .when()
                .get(Routes.BOOKS)
                .then()
                .statusCode(200);
    }

    @Test(priority = 3)
    public void getSingleBook() {

        given()
                .pathParam("bookId", 1)
                .when()
                .get(Routes.SINGLE_BOOK)
                .then()
                .statusCode(200)
                .body("id", org.hamcrest.Matchers.equalTo(1));
    }

    @Test(priority = 4)
    public void submitOrder() {

        String token = TokenManager.getToken();

        OrderPayload payload = new OrderPayload(1, "Thiyagu");

        orderId =
                given()
                        .contentType(ContentType.JSON)
                        .header("Authorization", "Bearer " + token)
                        .body(payload)
                        .when()
                        .post(Routes.ORDERS)
                        .then()
                        .statusCode(201)
                        .extract()
                        .path("orderId");

        System.out.println("Order Created: " + orderId);
        Assert.assertNotNull(orderId);
    }

    @Test(priority = 5)
    public void getAllOrders() {

        String token = TokenManager.getToken();

        given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get(Routes.ORDERS)
                .then()
                .statusCode(200);
    }

    @Test(priority = 6)
    public void getSingleOrder() {

        String token = TokenManager.getToken();

        given()
                .header("Authorization", "Bearer " + token)
                .pathParam("orderId", orderId)
                .when()
                .get(Routes.SINGLE_ORDER)
                .then()
                .statusCode(200)
                .body("id", org.hamcrest.Matchers.equalTo(orderId));
    }

    @Test(priority = 7)
    public void updateOrderUsingPatch() {

        String token = TokenManager.getToken();

        String updatedBody = "{ \"customerName\": \"UpdatedThiyagu\" }";

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .pathParam("orderId", orderId)
                .body(updatedBody)
                .when()
                .patch(Routes.SINGLE_ORDER)
                .then()
                .statusCode(204);
    }

    @Test(priority = 8)
    public void deleteOrder() {

        String token = TokenManager.getToken();

        given()
                .header("Authorization", "Bearer " + token)
                .pathParam("orderId", orderId)
                .when()
                .delete(Routes.SINGLE_ORDER)
                .then()
                .statusCode(204);
    }

    @Test(priority = 9)
    public void verifyOrderDeleted() {

        String token = TokenManager.getToken();

        given()
                .header("Authorization", "Bearer " + token)
                .pathParam("orderId", orderId)
                .when()
                .get(Routes.SINGLE_ORDER)
                .then()
                .statusCode(404);
    }
}