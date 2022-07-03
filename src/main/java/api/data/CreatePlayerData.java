package api.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "grant_type",
        "scope"
})
public class CreatePlayerData {

    @JsonProperty("username")
    private String username;

    @JsonProperty("password_change")
    private String password_change;

    @JsonProperty("password_repeat")
    private String password_repeat;

    @JsonProperty("email")
    private String email;

    @JsonProperty("name")
    private String name;

    @JsonProperty("surname")
    private String surname;

    public CreatePlayerData(String username, String password_change, String password_repeat, String email, String name, String surname) {
        this.username = username;
        this.password_change = password_change;
        this.password_repeat = password_repeat;
        this.email = email;
        this.name = name;
        this.surname = surname;
    }
}
