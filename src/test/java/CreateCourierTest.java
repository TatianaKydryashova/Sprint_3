import api.CourierApi;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CreateCourierTest {

    @Test
    @DisplayName("Check create courier")
    @Description("Courier creating checking")
    public void checkCreateCourierTest() {
        CourierApi courierApi = new CourierApi();
        String courierLogin = RandomStringUtils.randomAlphabetic(10);
        String courierPassword = RandomStringUtils.randomAlphabetic(10);
        String courierFirstName = RandomStringUtils.randomAlphabetic(10);
        Response response = courierApi.requestCreateCourier(courierLogin, courierPassword, courierFirstName);
        courierApi.checkStatusCodeCreateCouriersOk(response);
        courierApi.checkResponseBodyCreateCourierOK(response);
        int id = courierApi.requestIdCourier(courierLogin, courierPassword).then().extract().body().path("id");
        courierApi.requestDeleteCourier(id);
    }

    @Test
    @DisplayName("Check create two identical courier")
    @Description("Impossible to create two identical couriers")
    public void checkCreateTwoIdenticalCourierTest() {
        CourierApi courierApi = new CourierApi();
        String courierLogin = RandomStringUtils.randomAlphabetic(10);
        String courierPassword = RandomStringUtils.randomAlphabetic(10);
        String courierFirstName = RandomStringUtils.randomAlphabetic(10);
        Response responseFirst = courierApi.requestCreateCourier(courierLogin, courierPassword, courierFirstName);
        courierApi.checkStatusCodeCreateCouriersOk(responseFirst);
        courierApi.checkResponseBodyCreateCourierOK(responseFirst);
        int id = courierApi.requestIdCourier(courierLogin, courierPassword).then().extract().body().path("id");
        Response responseSecond = courierApi.requestCreateCourier(courierLogin, courierPassword, courierFirstName);
        courierApi.checkStatusCodeCreateCouriersConflictLogin(responseSecond);
        courierApi.checkResponseBodyCreateCourierConflictLogin(responseSecond);
        courierApi.requestDeleteCourier(id);
    }

    @Test
    @DisplayName("Check create courier with required fields")
    @Description("It is necessary to pass all required fields for creating a courier")
    public void checkCreateCourierWithRequiredFieldsTest(){
        CourierApi courierApi = new CourierApi();
        String courierLogin = RandomStringUtils.randomAlphabetic(10);
        String courierPassword = RandomStringUtils.randomAlphabetic(10);
        Response response = courierApi.requestCreateCourierWithRequiredFields(courierLogin, courierPassword);
        courierApi.checkStatusCodeCreateCouriersOk(response);
        courierApi.checkResponseBodyCreateCourierOK(response);
        int id = courierApi.requestIdCourier(courierLogin, courierPassword).then().extract().body().path("id");
        courierApi.requestDeleteCourier(id);
    }

    @Test
    @DisplayName("Check create courier without field")
    @Description("Request returns an error, if any field is missing")
    public void checkCreateCourierWithoutFieldTest(){
        CourierApi courierApi = new CourierApi();
        String courierLogin = RandomStringUtils.randomAlphabetic(10);
        String courierFirstName = RandomStringUtils.randomAlphabetic(10);
        Response response = courierApi.requestCreateCourierWithoutField(courierLogin, courierFirstName);
        courierApi.checkStatusCodeCouriersWithoutField(response);
        courierApi.checkResponseBodyCreateCourierWithoutField(response);
    }

    @Test
    @DisplayName("Check create courier identical login")
    @Description("Error is returned, if user is created with a username that already exists")
    public void checkCreateCourierIdenticalLoginTest(){
        CourierApi courierApi = new CourierApi();
        String courierLogin = RandomStringUtils.randomAlphabetic(10);
        String courierPassword = RandomStringUtils.randomAlphabetic(10);
        Response responseFirst = courierApi.requestCreateCourierWithRequiredFields(courierLogin, courierPassword);
        courierApi.checkStatusCodeCreateCouriersOk(responseFirst);
        courierApi.checkResponseBodyCreateCourierOK(responseFirst);
        int id = courierApi.requestIdCourier(courierLogin, courierPassword).then().extract().body().path("id");
        Response responseSecond = courierApi.requestCreateCourierWithRequiredFields(courierLogin, courierPassword);
        courierApi.checkStatusCodeCreateCouriersConflictLogin(responseSecond);
        courierApi.checkResponseBodyCreateCourierConflictLogin(responseSecond);
        courierApi.requestDeleteCourier(id);

    }

}
