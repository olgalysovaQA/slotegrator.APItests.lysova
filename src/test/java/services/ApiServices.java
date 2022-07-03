package services;

import api.config.ProjectConfig;
import api.data.AuthPlayerData;
import api.data.CreatePlayerData;
import api.data.TokenRequestData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Locale;

public class ApiServices {
    Faker faker = new Faker(Locale.ENGLISH);
    ProjectConfig config = ConfigFactory.create(ProjectConfig.class);


    public Response generateToken() {

        TokenRequestData tokenRequestData = new TokenRequestData("client_credentials", "guest:default");
        RequestSpecification request = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Basic " + Base64.getEncoder()
                        .encodeToString(config.apiusername().getBytes(StandardCharsets.UTF_8))).
                body(tokenRequestData);
        return request.post(config.apiurl() + "v2/oauth2/token");
    }

    public String getGuestToken() {
        return generateToken().jsonPath().getString("access_token");
    }

    public Response registerPlayer() {

        String name = faker.regexify("[a-z]{7,9}");
        CreatePlayerData createPlayerData = new CreatePlayerData(name, config.apipassword(), config.apipassword(), name + "@example.com", name, name);

        RequestSpecification request = RestAssured.given()
                .body(createPlayerData)
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + getGuestToken());
        return request.post(config.apiurl() + "v2/players");
    }

    public Response authorization() {

        AuthPlayerData authPlayerData = new AuthPlayerData("password", config.username(), config.apipassword());
        RequestSpecification request = RestAssured.given()
                .body(authPlayerData)
                .contentType(ContentType.JSON)
                .header("Authorization", "Basic " + Base64.getEncoder()
                        .encodeToString(config.apiusername().getBytes(StandardCharsets.UTF_8)));
        return request.post(config.apiurl() + "v2/oauth2/token");
    }

    public String authToken() {
        return authorization().jsonPath().getString("access_token");
    }

    public Response getPlayerInfo(Integer userID, String token) {
        RequestSpecification getRequest = RestAssured.given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token);
        return getRequest.get(config.apiurl() + "v2/players/" + userID);
    }
}
