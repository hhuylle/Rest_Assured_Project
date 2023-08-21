package com.cydeo.homework;

import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;


public class Homework1 extends HrTestBase {

    //- Given accept type is Json
    //- When users sends request to /countries/US
    //- Then status code is 200
    //- And Content - Type is application/json
    //- And response contains United States of America
    @DisplayName("Get /countries/US")
    @Test
    public void task1(){

        Response response = given().log().uri().accept(ContentType.JSON)
                .when().get("/countries/US");

        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.contentType());

        assertTrue(response.body().asString().contains("United States of America"));
    }


    //- Given accept type is Json
    //- When users sends request to /employees/1
    //- Then status code is 404
    @DisplayName("GET /employees/1")
    @Test
    public void task2(){

        Response response = given().accept(ContentType.JSON)
                .when().get("/employees/1");

        assertEquals(404,response.statusCode());

    }


    //- Given accept type is Json
    //- When users sends request to /regions/1
    //- Then status code is 200
    //- And Content - Type is application/json
    //- And response contains Europe
    //- And header should contains Date
    //- And Transfer-Encoding should be chunked
    @DisplayName("GET /regions/1")
    @Test
    public void task3(){

        Response response = given().accept(ContentType.JSON)
                .when().get("/regions/1");

        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.contentType());
        assertTrue(response.body().asString().contains("Europe"));
        assertTrue(response.headers().hasHeaderWithName("Date"));
        assertEquals("chunked",response.getHeader("Transfer-Encoding"));
    }


}
