
import api.OrderApi;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import requests.CreateOrderRequest;
import java.util.List;
import java.util.stream.Stream;

public class CreateOrderTest {
    @ParameterizedTest
    @DisplayName("Check create order")
    @Description("Order creating with parametrization")
    @ArgumentsSource(value = Colors.class)
    public void checkCreateOrderTest(List<String> color) {
        OrderApi orderApi = new OrderApi();
        CreateOrderRequest createOrderRequest = CreateOrderRequest.build();
        createOrderRequest.setColor(color);
        Response response = orderApi.requestCreateOrder(createOrderRequest);
        orderApi.checkStatusCodeCreateOrderOk(response);
        orderApi.checkResponseBodyCreateOrderOK(response);
    }
    public static class Colors implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of(
                    Arguments.of(List.of("BLACK")),
                    Arguments.of(List.of("GREY")),
                    Arguments.of(List.of("BLACK","GREY")),
                    Arguments.of(List.of(""))
            );
        }
    }

}
