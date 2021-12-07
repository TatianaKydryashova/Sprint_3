package api;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import requests.CreateCourierRequest;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;

public class CourierApi {

    @Step("Send POST request to /api/v1/courier")
    public Response requestCreateCourier(CreateCourierRequest createCourierRequest) {
        return given()
                .when()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(createCourierRequest)
                .post(EndpointsCourierApi.POST_CREATE_COURIER);
    }

    @Step("Send POST request to /api/v1/courier/login")
    public Response requestIdCourier(CreateCourierRequest createCourierRequest) {
        return given()
                .when()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(createCourierRequest)
                .post(EndpointsCourierApi.POST_LOGIN_COURIER);
    }

    @Step("Send DELETE request to /api/v1/courier/{id}")
    public Response requestDeleteCourier(int id) {
        return given()
                .when()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .delete(EndpointsCourierApi.DELETE_COURIER, id);
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
    public void checkResponseBodyAuthorizationCourierOK(Response response){
        response.then().assertThat().body("id", notNullValue());
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

