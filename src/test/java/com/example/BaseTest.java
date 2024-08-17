package com.example;

import org.hamcrest.Matchers;
import org.junit.BeforeClass;

import io.restassured.*;
import io.restassured.builder.*;

public class BaseTest implements Constants {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = BASE_URL;
        
        RequestSpecBuilder reqBuilder = new RequestSpecBuilder();
        reqBuilder.setContentType(CONTENT_TYPE);
        RestAssured.requestSpecification = reqBuilder.build();

        ResponseSpecBuilder resBuilder = new ResponseSpecBuilder();
        resBuilder.expectResponseTime(Matchers.lessThan(MAX_TIMEOUT));
        RestAssured.responseSpecification = resBuilder.build();

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        
    }
}
