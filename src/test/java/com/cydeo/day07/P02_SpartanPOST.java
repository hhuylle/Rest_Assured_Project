package com.cydeo.day07;

import com.cydeo.pojo.Spartan;
import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

public class P02_SpartanPOST extends SpartanTestBase {

    /**
     Given accept type is JSON
     And Content type is JSON
     And request json body is:
     {
     "gender":"Male",
     "name":"John Doe",
     "phone":8877445596
     }
     When user sends POST request to '/api/spartans'
     Then status code 201
     And content type should be application/json
     And json payload/response/body should contain:
     verify the success value is 'A Spartan is Born!'
     "name": "John Doe",
     "gender": "Male",
     "phone": 8877445596
     */

    @DisplayName("POST Spartan with String body")
    @Test
    public void test1(){

        String requestBody = "{\n" +
                "     \"gender\":\"Male\",\n" +
                "     \"name\":\"John Doe\",\n" +
                "     \"phone\":8877445596\n" +
                "     }";

        JsonPath jp = given().log().uri()
                .accept(ContentType.JSON)//hey api, send me JSON RESPONSE
                .contentType(ContentType.JSON)//hey api, I'm sending you JSON REQUEST
                .body(requestBody)
                .when().post("/api/spartans").prettyPeek()
                .then().statusCode(201)
                .contentType(ContentType.JSON.toString())
                .body("success", is("A Spartan is Born!"))
                .extract().jsonPath();

        //Request body verification
       assertEquals("John Doe",jp.getString("data.name"));
        assertEquals("Male",jp.getString("data.gender"));
        assertEquals("8877445596",jp.getString("data.phone"));

        //I want to get id out of the response body, to delete or send GET request later on
        int id = jp.getInt("data.id");
        System.out.println("id = " + id);

    }


    @DisplayName("POST Spartan with Map body")
    @Test
    public void test2(){

        Map<String, Object> requestBodyMap = new LinkedHashMap<>();
        requestBodyMap.put("name","John Doe");
        requestBodyMap.put("gender","Male");
        requestBodyMap.put("phone","8877445596");
        //we create one map, and put the info that we want to send as s JSON REQUEST BODY
        System.out.println("requestBodyMap = " + requestBodyMap);

        JsonPath jp = given().log().body()
                .accept(ContentType.JSON)//hey api, send me JSON RESPONSE
                .contentType(ContentType.JSON)//hey api, I'm sending you JSON REQUEST
                .body(requestBodyMap)
                .when().post("/api/spartans").prettyPeek()
                .then().statusCode(201)
                .contentType(ContentType.JSON.toString())
                .body("success", is("A Spartan is Born!"))
                .extract().jsonPath();

        //Request body verification
        assertEquals("John Doe",jp.getString("data.name"));
        assertEquals("Male",jp.getString("data.gender"));
        assertEquals("8877445596",jp.getString("data.phone"));

        //I want to get id out of the response body, to delete or send GET request later on
        int id = jp.getInt("data.id");
        System.out.println("id = " + id);

        //can we have SpartanUtil class which is giving as a requestMap with dynamic dummny values ?
        //using faker library inside ?
    }

    @DisplayName("POST Spartan with Spartan POJO")
    @Test
    public void test3(){
        Spartan spartan = new Spartan();
        spartan.setName("Harold Finch");
        spartan.setGender("Male");
        spartan.setPhone(1234567890l);

        System.out.println("spartan = " + spartan);

        JsonPath jp = given().log().body()
                .accept(ContentType.JSON)//hey api, send me JSON RESPONSE
                .contentType(ContentType.JSON)//hey api, I'm sending you JSON REQUEST
                .body(spartan)
                .when().post("/api/spartans").prettyPeek()
                .then().statusCode(201)
                .contentType(ContentType.JSON.toString())
                .body("success", is("A Spartan is Born!"))
                .extract().jsonPath();

        //Request body verification
        assertEquals("Harold Finch",jp.getString("data.name"));
        assertEquals("Male",jp.getString("data.gender"));
        assertEquals("1234567890",jp.getString("data.phone"));

        //I want to get id out of the response body, to delete or send GET request later on
        int id = jp.getInt("data.id");
        System.out.println("id = " + id);
    }

    @DisplayName("POST Spartan with Spartan POJO and GET same Spartan")
    @Test
    public void test4(){
        //empty spartan object and we use setters to set some value
        //values can be from faker library or somewhere else which is changing dynamically
        Spartan spartanPOST = new Spartan();
        spartanPOST.setName("Harold Finch");
        spartanPOST.setGender("Male");
        spartanPOST.setPhone(1234567890l);
        spartanPOST.setId(500); //even if we put some id value, it didn't serialize because of the jackson annotation on the class

        System.out.println("spartan = " + spartanPOST);

        JsonPath jp = given().log().body()
                .accept(ContentType.JSON)//hey api, send me JSON RESPONSE
                .contentType(ContentType.JSON)//hey api, I'm sending you JSON REQUEST
                .body(spartanPOST)//it will take spartan object and serialize to JSON
                .when().post("/api/spartans").prettyPeek()
                .then().statusCode(201)
                .contentType(ContentType.JSON.toString())
                .body("success", is("A Spartan is Born!"))
                .extract().jsonPath();

        //Request body verification
        assertEquals("Harold Finch",jp.getString("data.name"));
        assertEquals("Male",jp.getString("data.gender"));
        assertEquals("1234567890",jp.getString("data.phone"));

        //I want to get id out of the response body, to delete or send GET request later on
        int id = jp.getInt("data.id");
        System.out.println("id = " + id);

        //SEND GET REQUEST TO THE SPARTAN THAT IS CREATED THEN DESERIALIZE TO SPARTAN CLASS AND COMPARE
        Response response = given().accept(ContentType.JSON)
                .pathParam("id", id)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200)
                .extract().response();

        //Get JSON Response and deserialization
        Spartan spartanGET = response.as(Spartan.class);

        System.out.println("spartanGET = " + spartanGET);

        //Verify names that we sent and get is matching
        assertEquals(spartanPOST.getName(),spartanGET.getName());
    }
}
