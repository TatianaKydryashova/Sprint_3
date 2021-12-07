package api;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import requests.CreateOrderRequest;
import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsNull.notNullValue;

public class OrderApi {

    @Step("Send POST request to /api/v1/orders")
    public Response requestCreateOrder(CreateOrderRequest createOrderRequest) {
        return given()
                .when()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(createOrderRequest)
                .post(EndpointsOrderApi.POST_CREATE_ORDERS);
    }

    @Step("Send Get request to /api/v1/orders?limit=10&page=0")
    public Response requestOrderList() {
        return given()
                .when()
                .header("Content-type", "application/json")
                .get(EndpointsOrderApi.GET_10AVAILABLE_ORDERS);
    }

    @Step("Check statusCode create Order List Ok")
    public void checkStatusCodeCreateOrderListOk(Response response){
        response.then().assertThat().statusCode(200);
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
