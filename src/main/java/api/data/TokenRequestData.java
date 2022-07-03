package api.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "grant_type",
        "scope"
})
public class TokenRequestData {

    @JsonProperty("grant_type")
    private String grant_type;

    @JsonProperty("scope")
    private String scope;

    public TokenRequestData(String grant_type, String scope) {
        this.grant_type = grant_type;
        this.scope = scope;
    }

}
