package com.cydeo.day03;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P01_SpartanWithParameters extends SpartanTestBase{

     /*   Given accept type is Json
        And Id parameter value is 24
        When user sends GET request to /api/spartans/{id}
        Then response status code should be 200
        And response content-type: application/json
        And "Julio" should be in response payload(body)
     */
    @DisplayName("GET Spartan /api/spartans/{id} path param with 24")
    @Test
    public void test1() {

        //Then response status code should be 200
        Response response = given()
                        .accept(ContentType.JSON)
                        .and()
                        .pathParam("id",24)
                        .when().get("/api/spartans/{id}");
        response.prettyPrint();

        assertEquals(200, response.getStatusCode());
        //And response content-type: application/json
        assertEquals("application/json", response.getContentType());
        //And "Julio" should be in response payload(body)

        boolean isJulio = response.body().asString().contains("Julio");
        assertTrue(isJulio);

    }

     /*
        TASK
        Given accept type is Json
        And Id parameter value is 500
        When user sends GET request to /api/spartans/{id}
        Then response status code should be 404
        And response content-type: application/json
        And "Not Found" message should be in response payload
      */
    @DisplayName("GET Spartan /api/spartans/{id} with invalid ID")
    @Test
    public void test2() {


        //print response
        Response response = given().accept(ContentType.JSON)
                .and() //Syntactic sugar--> to increase readability of the code, not affect anything
                .pathParam("id", 500)
                .when()
                .get("/api/spartans/{id}");
        //Then response status code should be 404
        assertEquals(404,response.statusCode());

        //same with above
        assertEquals(HttpStatus.SC_NOT_FOUND,response.statusCode());

        // response content-type: application/json
        assertEquals("application/json", response.contentType());

        // And "Not Found" message should be in response payload
        assertTrue(response.body().asString().contains("Not Found"));

    }

     /*
        Given Accept type is Json
        And query parameter values are:
            gender|Female
            nameContains|e
        When user sends GET request to /api/spartans/search
        Then response status code should be 200
        And response content-type: application/json
        And "Female" should be in response payload
        And "Janette" should be in response payload
     */
    @DisplayName("GET Request to /api/spartans/search with Query Params")
    @Test
    public void test3(){

       //print response
        Response response = given().accept(ContentType.JSON)
                .and()
                .queryParam("gender", "Female")
                .and()
                .queryParam("nameContains", "e")
                .when()
                .get("/api/spartans/search");

//      Then response status code should be 200
        assertEquals(200,response.statusCode());

//      And response content-type: application/json
        assertEquals("application/json",response.contentType());
//      And "Female" should be in response payload
        assertTrue(response.body().asString().contains("Female"));
//      And "Janette" should be in response payload
        assertTrue(response.body().asString().contains("Janette"));
        //assertTrue(response.body().asString().contains("Janette"));
        /*
            We are just doing exercise to verify something in Response. THis is not the proper way to verify
            all Spartans genders are Female, or name field is Janette, We will learn different method to get specific data.
         */


    }
    @DisplayName("GET Request to /api/spartans/search with Query Params")
    @Test
    public void test4(){
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("gender","Female");
        queryMap.put("nameContains","e");

        //api/spartans/search?gender=Female&nameContains=e
        Response response = given().accept(ContentType.JSON)
                .and()
                .queryParams(queryMap)
                .when()
                .get("/api/spartans/search");
        //print response
        response.prettyPrint();

//      Then response status code should be 200
        assertEquals(200,response.statusCode());

//      And response content-type: application/json
        assertEquals("application/json",response.contentType());
//      And "Female" should be in response payload
        assertTrue(response.body().asString().contains("Female"));
//      And "Janette" should be in response payload
        assertTrue(response.body().asString().contains("Janette"));

    }





}
