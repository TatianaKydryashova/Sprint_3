package api;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import requests.CreateOrderRequest;
import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsNull.notNullValue;

public class OrderApi {
    public OrderApi() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
    }

    @Step("Send POST request to /api/v1/orders")
    public Response requestCreateOrder(CreateOrderRequest createOrderRequest) {
        return given()
                .when()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(createOrderRequest)
                .post("/api/v1/orders");
    }

    @Step("Send Get request to /api/v1/orders?limit=10&page=0")
    public Response requestOrderList() {
        return given()
                .when()
                .header("Content-type", "application/json")
                .get("/api/v1/orders?limit=10&page=0");
    }

    @Step("Check response body Order List")
    public void checkResponseBodyOrderList(Response response){
        response.then().assertThat().body("orders", notNullValue());
    }

    @Step("Check statusCode create Order OK")
    public void checkStatusCodeCreateOrderOk(Response response){
        response.then().assertThat().statusCode(201);
    }

    @Step("Check response body create Order OK")
    public void checkResponseBodyCreateOrderOK(Response response){
        response.then().assertThat().body("track", notNullValue());
    }

}
