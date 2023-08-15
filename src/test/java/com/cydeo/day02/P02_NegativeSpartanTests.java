package com.cydeo.day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class P02_NegativeSpartanTests {

    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "http://54.165.189.254:8000";
    }

    /*
     * Given Accept content-type is application/json
     * When user sends GET request /api/spartans endpoint
     * Then status code should be 200
     */

    @DisplayName("GET - ALL SPARTANS")
    @Test
    public void getAllSpartans(){

        Response response = given().accept(ContentType.JSON).when().get("/api/spartans");

        Assertions.assertEquals(200, response.getStatusCode());
        response.prettyPrint();

    }

       /*
        Given Accept type is application/xml
        When user send GET request to /api/spartans/10 end point
        Then status code must be 406
        And response Content Type must be application/xml;charset=UTF-8;
        */
    @DisplayName("GET- All Spartans -Accept, application/xml - 406")
    @Test
    public void xmlTest(){

        Response response = given().accept(ContentType.XML)
                .when().get("/api/spartans/10");
        //verify status code is 406
        Assertions.assertEquals(406,response.getStatusCode());

        //response Content Type must be application/xml;charset=UTF-8;
        Assertions.assertEquals("application/xml;charset=UTF-8" , response.contentType());

    }

}
