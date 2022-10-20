package com.example.gitapitest.api.service;

public interface OAuthService {
    String getAccessToken(String code);
    String getUserName(String accessToken);
}
