package com.example.gitapitest.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class OAuthAccessTokenResponse {
    @JsonProperty("access_token")
    String accessToken;
    @JsonProperty("scope")
    String scope;
    @JsonProperty("token_type")
    String tokenType;
}
