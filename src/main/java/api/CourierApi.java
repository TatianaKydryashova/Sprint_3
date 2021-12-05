package api;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CourierApi {
    public CourierApi() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
    }

    @Step("Send POST request to /api/v1/courier")
    public Response requestCreateCourier(String login, String password, String firstName) {
        String registerRequestBody = "{\"login\":\"" + login + "\","
                + "\"password\":\"" + password + "\","
                + "\"firstName\":\"" + firstName + "\"}";
        return given()
                .when()
                .header("Content-type", "application/json")
                .body(registerRequestBody)
                .post("/api/v1/courier");

    }

    @Step("Send POST request to /api/v1/courier with required fields")
    public Response requestCreateCourierWithRequiredFields(String login, String password) {
        String registerRequestBody = "{\"login\":\"" + login + "\","
                + "\"password\":\"" + password + "\"}";
        return given()
                .when()
                .header("Content-type", "application/json")
                .body(registerRequestBody)
                .post("/api/v1/courier");

    }

    @Step("Send POST request to /api/v1/courier without field")
    public Response requestCreateCourierWithoutField(String login, String firstName) {
        String registerRequestBody = "{\"login\":\"" + login + "\","
                + "\"firstName\":\"" + firstName + "\"}";
        return given()
                .when()
                .header("Content-type", "application/json")
                .body(registerRequestBody)
                .post("/api/v1/courier");

    }

    @Step("Send POST request to /api/v1/courier/login")
    public Response requestIdCourier(String login, String password) {
        String registerRequestBody = "{\"login\":\"" + login + "\","
                + "\"password\":\"" + password + "\"}";
        return given()
                .when()
                .header("Content-type", "application/json")
                .body(registerRequestBody)
                .post("/api/v1/courier/login");
    }

    @Step("Send POST request to /api/v1/courier/login with one login")
    public Response requestIdCourierWithOneLogin(String login) {
        String registerRequestBody = "{\"login\":\"" + login + "\"}";
        return given()
                .when()
                .header("Content-type", "application/json")
                .body(registerRequestBody)
                .post("/api/v1/courier/login");
    }

    @Step("Send DELETE request to /api/v1/courier/{id}")
    public Response requestDeleteCourier(int id) {
        return given()
                .when()
                .header("Content-type", "application/json")
                .delete("/api/v1/courier/{id}", id);
    }

    @Step("Check statusCode create couriers OK")
    public void checkStatusCodeCreateCouriersOk(Response response){
        response.then().assertThat().statusCode(201);
    }

    @Step("Check response body create courier OK")
    public void checkResponseBodyCreateCourierOK(Response response){
        response.then().assertThat().body("ok", equalTo(true));
    }

    @Step("Check status code create couriers conflict login")
    public void checkStatusCodeCreateCouriersConflictLogin(Response response){
        response.then().assertThat().statusCode(409);
    }

    @Step("Check response body create courier conflict login")
    public void checkResponseBodyCreateCourierConflictLogin(Response response){
        response.then().assertThat().body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    @Step("Check status code couriers without field")
    public void checkStatusCodeCouriersWithoutField(Response response){
        response.then().assertThat().statusCode(400);
    }

    @Step("Check response body create courier without field")
    public void checkResponseBodyCreateCourierWithoutField(Response response){
        response.then().assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Step("Check statusCode authorization couriers OK")
    public void checkStatusCodeAuthorizationCouriersOk(Response response){
        response.then().assertThat().statusCode(200);
    }

    @Step("Check response body authorization courier OK")
    public void checkResponseBodyAuthorizationCourierOK(Response response, int id){
        response.then().assertThat().body("id", equalTo(id));
    }

    @Step("Check statusCode authorization couriers with a non-existent pair of login / password")
    public void checkStatusCodeAuthorizationCouriersWithNonExistentLoginPass(Response response){
        response.then().assertThat().statusCode(404);
    }

    @Step("Check response body authorization courier with a non-existent pair of login / password")
    public void checkResponseBodyAuthorizationCourierWithNonExistentLoginPass(Response response){
        response.then().assertThat().body("message", equalTo("Учетная запись не найдена"));
    }

    @Step("Check response body authorization courier without field")
    public void checkResponseBodyAuthorizationCourierWithoutField(Response response){
        response.then().assertThat().body("message", equalTo("Недостаточно данных для входа"));
    }

}

