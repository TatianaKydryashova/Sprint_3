import api.CourierApi;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
public class CourierLoginTest {

    @Test
    @DisplayName("Check authorization courier")
    @Description("Сourier authorization сhecking")
    public void checkAuthorizationCourierTest(){
        ScooterRegisterCourier scooterRegisterCourier = new ScooterRegisterCourier();
        ArrayList<String> loginPass = scooterRegisterCourier.registerNewCourierAndReturnLoginPassword();
        CourierApi courierApi = new CourierApi();
        Response response = courierApi.requestIdCourier(loginPass.get(0), loginPass.get(1));
        int id = response.then().extract().body().path("id");
        courierApi.checkStatusCodeAuthorizationCouriersOk(response);
        courierApi.checkResponseBodyAuthorizationCourierOK(response, id);
        courierApi.requestDeleteCourier(id);
    }

    @Test
    @DisplayName("Check authorization courier with a non-existent pair of login / pass")
    @Description("The system will return an error if username or password is incorrect")
    public void checkAuthorizationCourierWithNonExistentLoginOrPassTest(){
        String courierLogin = RandomStringUtils.randomAlphabetic(10);
        String courierPassword = RandomStringUtils.randomAlphabetic(10);
        CourierApi courierApi = new CourierApi();
        Response response = courierApi.requestIdCourier(courierLogin, courierPassword);
        courierApi.checkStatusCodeAuthorizationCouriersWithNonExistentLoginPass(response);
        courierApi.checkResponseBodyAuthorizationCourierWithNonExistentLoginPass(response);
    }

    @Test
    @DisplayName("Check authorization courier with one login")
    @Description("If any field is missing, the request returns an error")
    public void checkAuthorizationCourierWithOneLoginTest(){
        ScooterRegisterCourier scooterRegisterCourier = new ScooterRegisterCourier();
        ArrayList<String> loginPass = scooterRegisterCourier.registerNewCourierAndReturnLoginPassword();
        CourierApi courierApi = new CourierApi();
        Response responseAdd = courierApi.requestIdCourier(loginPass.get(0), loginPass.get(1));
        int id = responseAdd.then().extract().body().path("id");
        Response responseNotAdd = courierApi.requestIdCourierWithOneLogin(loginPass.get(0));
        courierApi.checkStatusCodeCouriersWithoutField(responseNotAdd);
        courierApi.checkResponseBodyAuthorizationCourierWithoutField(responseNotAdd);
        courierApi.requestDeleteCourier(id);
    }



}
