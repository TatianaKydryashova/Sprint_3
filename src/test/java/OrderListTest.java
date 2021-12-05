import api.OrderApi;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OrderListTest {
    @Test
    @DisplayName("Check order list")
    @Description("Orders list is returned in response body")
    public void checkOrderListTest(){
        OrderApi orderApi = new OrderApi();
        Response response = orderApi.requestOrderList();
        orderApi.checkResponseBodyOrderList(response);
    }
}
