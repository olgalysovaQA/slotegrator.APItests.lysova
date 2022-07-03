package tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import services.ApiServices;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertAll;

public class ApiTests {


    ApiServices apiService = new ApiServices();

    Integer id = 11996;


    @Test
    @DisplayName("Return guest token")
    public void getToken() {

        Response response = apiService.generateToken();
        response.then().assertThat().body("access_token", not(nullValue()));
        assertAll(
                () -> assertThat(response.getStatusCode(), is(200)),
                () -> assertThat(response.then().extract().path("access_token"), is(not(emptyString()))));
    }

    @Test
    @DisplayName("Creating a new player")
    public void playerRegistration() {

        Response response = apiService.registerPlayer();
        assertAll(
                () -> assertThat(response.statusCode(), is(201)));
    }

    @Test
    @DisplayName("Authorization")
    public void authorizationPlayer() {

        Response response = apiService.authorization();
        assertAll(
                () -> assertThat(response.getStatusCode(), is(200)),
                () -> assertThat(response.jsonPath().getString("access_token"), is(not(emptyString()))));
    }

    @Test
    @DisplayName("Return player info")
    public void returnPlayerInfo() {

        String token = apiService.authToken();
        Response response = apiService.getPlayerInfo(id, token);
        assertAll(
                () -> assertThat(response.getStatusCode(), is(200)),
                () -> assertThat(response.jsonPath().get("id"), equalTo(id)));
    }

    @Test
    @DisplayName("Return info of player with wrong id")
    public void returnPlayerInfoWithWrongId() {

        String token = apiService.authToken();
        int wrongId = id + 11;

        Response getResponse = apiService.getPlayerInfo(wrongId, token);
        assertAll(
                () -> assertThat(getResponse.getStatusCode(), is(404)));
    }

}

