import api.CourierApi;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import requests.CreateCourierRequest;

public class CourierLoginTest {

    CreateCourierRequest createCourierRequest;
    int id;

    @BeforeEach
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
        CourierApi courierApi = new CourierApi();
        createCourierRequest = CreateCourierRequest.buildRequiredFields();
        courierApi.requestCreateCourier(createCourierRequest);
        id = courierApi.requestIdCourier(createCourierRequest).then().extract().body().path("id");
    }

    @Test
    @DisplayName("Check authorization courier")
    @Description("Сourier authorization сhecking")
    public void checkAuthorizationCourierTest() {
        CourierApi courierApi = new CourierApi();
        Response response = courierApi.requestIdCourier(createCourierRequest);
        courierApi.checkStatusCodeAuthorizationCouriersOk(response);
        courierApi.checkResponseBodyAuthorizationCourierOK(response);
    }
    @Test
    @DisplayName("Check authorization courier with a non-existent pair of login / pass")
    @Description("The system will return an error if username or password is incorrect")
    public void checkAuthorizationCourierWithNonExistentLoginOrPassTest() {
        CourierApi courierApi = new CourierApi();
        createCourierRequest.setLogin(RandomStringUtils.randomAlphabetic(10));
        Response response = courierApi.requestIdCourier(createCourierRequest);
        courierApi.checkStatusCodeAuthorizationCouriersWithNonExistentLoginPass(response);
        courierApi.checkResponseBodyAuthorizationCourierWithNonExistentLoginPass(response);
    }

    @Test
    @DisplayName("Check authorization courier with one login")
    @Description("If any field is missing, the request returns an error")
    public void checkAuthorizationCourierWithOneLoginTest(){
        CourierApi courierApi = new CourierApi();
        createCourierRequest.setPassword("");
        Response response = courierApi.requestIdCourier(createCourierRequest);
        courierApi.checkStatusCodeCouriersWithoutField(response);
        courierApi.checkResponseBodyAuthorizationCourierWithoutField(response);
    }

    @AfterEach
    public void deleteCourier() {
        CourierApi courierApi = new CourierApi();
        if (createCourierRequest.getLogin() != null && createCourierRequest.getPassword() != null) {
            courierApi.requestDeleteCourier(id);
        }
    }
}
