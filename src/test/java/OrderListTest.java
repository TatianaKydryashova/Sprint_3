import api.OrderApi;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OrderListTest {

    @BeforeEach
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru";
    }

    @Test
    @DisplayName("Check order list")
    @Description("Orders list is returned in response body")
    public void checkOrderListTest(){
        OrderApi orderApi = new OrderApi();
        Response response = orderApi.requestOrderList();
        orderApi.checkStatusCodeCreateOrderListOk(response);
        orderApi.checkResponseBodyOrderList(response);
    }
}
