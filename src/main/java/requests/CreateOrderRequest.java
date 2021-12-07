package requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class CreateOrderRequest {
    @JsonProperty("firstName")
    private String firstName;
    private String lastName;
    private String address;
    private int metroStation;
    private String phone;
    private int rentTime;
    private String deliveryDate;
    private String comment;
    private List<String> color ;

    public static CreateOrderRequest build(){
        CreateOrderRequest createOrderRequest = new CreateOrderRequest();
        createOrderRequest.setFirstName("Tatiana");
        createOrderRequest.setLastName("Smirnova");
        createOrderRequest.setAddress("Konoha, 142 apt.");
        createOrderRequest.setMetroStation(4);
        createOrderRequest.setPhone("+7 800 355 35 35");
        createOrderRequest.setRentTime(5);
        createOrderRequest.setDeliveryDate("2021-12-10");
        createOrderRequest.setComment("Saske, come back to Konoha");
        createOrderRequest.setColor(List.of("BLACK"));
        return createOrderRequest;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getMetroStation() {
        return metroStation;
    }

    public void setMetroStation(int metroStation) {
        this.metroStation = metroStation;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getRentTime() {
        return rentTime;
    }

    public void setRentTime(int rentTime) {
        this.rentTime = rentTime;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<String> getColor() {
        return color;
    }

    public void setColor(List<String> color) {
        this.color = color;
    }
}
