package requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.RandomStringUtils;

public class CreateCourierRequest {

    @JsonProperty("login")
    private String login;
    private String password;
    private String firstName;

    public static CreateCourierRequest buildAllFields(){
        CreateCourierRequest createCourierRequest = new CreateCourierRequest();
        createCourierRequest.setLogin(RandomStringUtils.randomAlphabetic(10));
        createCourierRequest.setPassword(RandomStringUtils.randomAlphabetic(10));
        createCourierRequest.setFirstName(RandomStringUtils.randomAlphabetic(10));
        return createCourierRequest;
    }

    public static CreateCourierRequest buildRequiredFields(){
        CreateCourierRequest createCourierRequest = new CreateCourierRequest();
        createCourierRequest.setLogin(RandomStringUtils.randomAlphabetic(10));
        createCourierRequest.setPassword(RandomStringUtils.randomAlphabetic(10));
        return createCourierRequest;
    }

    public static CreateCourierRequest buildWithoutRequiredFields(){
        CreateCourierRequest createCourierRequest = new CreateCourierRequest();
        createCourierRequest.setLogin(RandomStringUtils.randomAlphabetic(10));
        createCourierRequest.setFirstName(RandomStringUtils.randomAlphabetic(10));
        return createCourierRequest;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
