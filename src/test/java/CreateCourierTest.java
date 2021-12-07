import api.CourierApi;
import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import requests.CreateCourierRequest;

public class CreateCourierTest {

    CreateCourierRequest createCourierRequest;

    @BeforeEach
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
    }

    @Test
    @DisplayName("Check create courier")
    @Description("Courier creating checking")
    public void checkCreateCourierTest() {
        CourierApi courierApi = new CourierApi();
        createCourierRequest = CreateCourierRequest.buildAllFields();
        Response response = courierApi.requestCreateCourier(createCourierRequest);
        courierApi.checkStatusCodeCreateCouriersOk(response);
        courierApi.checkResponseBodyCreateCourierOK(response);
    }

    @Test
    @DisplayName("Check create two identical courier")
    @Description("Impossible to create two identical couriers")
    public void checkCreateTwoIdenticalCourierTest() {
        CourierApi courierApi = new CourierApi();
        createCourierRequest = CreateCourierRequest.buildAllFields();
        Response responseFirst = courierApi.requestCreateCourier(createCourierRequest);
        courierApi.checkStatusCodeCreateCouriersOk(responseFirst);
        courierApi.checkResponseBodyCreateCourierOK(responseFirst);
        Response responseSecond = courierApi.requestCreateCourier(createCourierRequest);
        courierApi.checkStatusCodeCreateCouriersConflictLogin(responseSecond);
        courierApi.checkResponseBodyCreateCourierConflictLogin(responseSecond);
    }

    @Test
    @DisplayName("Check create courier with required fields")
    @Description("It is necessary to pass all required fields for creating a courier")
    public void checkCreateCourierWithRequiredFieldsTest(){
        CourierApi courierApi = new CourierApi();
        createCourierRequest = CreateCourierRequest.buildRequiredFields();
        Response response = courierApi.requestCreateCourier(createCourierRequest);
        courierApi.checkStatusCodeCreateCouriersOk(response);
        courierApi.checkResponseBodyCreateCourierOK(response);
    }

    @Test
    @DisplayName("Check create courier without field")
    @Description("Request returns an error, if any field is missing")
    public void checkCreateCourierWithoutFieldTest(){
        CourierApi courierApi = new CourierApi();
        createCourierRequest = CreateCourierRequest.buildWithoutRequiredFields();
        Response response = courierApi.requestCreateCourier(createCourierRequest);
        courierApi.checkStatusCodeCouriersWithoutField(response);
        courierApi.checkResponseBodyCreateCourierWithoutField(response);
    }

    @Test
    @DisplayName("Check create courier identical login")
    @Description("Error is returned, if user is created with a username that already exists")
    public void checkCreateCourierIdenticalLoginTest(){
        CourierApi courierApi = new CourierApi();
        createCourierRequest = CreateCourierRequest.buildRequiredFields();
        Response responseFirst = courierApi.requestCreateCourier(createCourierRequest);
        courierApi.checkStatusCodeCreateCouriersOk(responseFirst);
        courierApi.checkResponseBodyCreateCourierOK(responseFirst);
        Response responseSecond = courierApi.requestCreateCourier(createCourierRequest);
        courierApi.checkStatusCodeCreateCouriersConflictLogin(responseSecond);
        courierApi.checkResponseBodyCreateCourierConflictLogin(responseSecond);
    }


    @AfterEach
    public void deleteCourier(){
        CourierApi courierApi = new CourierApi();
        createCourierRequest.setFirstName("");
        if (createCourierRequest.getLogin()!=null && createCourierRequest.getPassword()!=null){
        int id = courierApi.requestIdCourier(createCourierRequest).then().extract().body().path("id");
        courierApi.requestDeleteCourier(id);}
    }

}
