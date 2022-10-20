package com.example.gitapitest.api.service;

import com.example.gitapitest.api.request.OAuthAccessTokenRequest;
import com.example.gitapitest.api.response.OAuthAccessTokenResponse;
import com.example.gitapitest.api.response.OAuthMemberInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class OAuthServiceImpl implements OAuthService{
    @Value("${github.clientId}")
    private String clientId;
    @Value("${github.clientSecret}")
    private String clientSecret;

    private final RestTemplate restTemplate ;
    @Override
    public String getAccessToken(String code) {
        OAuthAccessTokenRequest requestBody = new OAuthAccessTokenRequest(clientId, clientSecret, code);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(requestBody.toString(), headers);

        return restTemplate.postForObject(
                "https://github.com/login/oauth/access_token",
                entity,
                OAuthAccessTokenResponse.class
        ).getAccessToken();
    }

    @Override
    public String getUserName(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        HttpEntity<Void> request = new HttpEntity<>(headers);

        return restTemplate.exchange(
                "https://api.github.com/user",
                HttpMethod.GET,
                request,
                OAuthMemberInfoResponse.class
        ).getBody().getName();
    }
}
