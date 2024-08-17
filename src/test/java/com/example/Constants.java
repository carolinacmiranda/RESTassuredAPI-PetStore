package com.example;

import io.restassured.http.ContentType;

public interface Constants {

    String BASE_URL = "https://petstore.swagger.io/v2";

    ContentType CONTENT_TYPE = ContentType.JSON;

    Long MAX_TIMEOUT = 5000L;
    
}
