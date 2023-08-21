package com.cydeo.sunlive.week2;

import com.cydeo.utilities.FruitTestBase;
import groovy.xml.StreamingDOMBuilder;
import io.restassured.http.ContentType;
import io.restassured.internal.common.assertion.Assertion;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class P01_PathParam extends FruitTestBase {
    /**
     *1- Given accept type is Json
     *2- Path Parameters value is
     *     - id —> 8
     *3- When user sends GET request to /products/{id}
     *4- Verify followings
     - Status code should be 200
     - Content Type is application/json
     - Print response
     - id is 8
     - Name is "Coconut"
     - Vendor name is "True Fruits Inc."

     */
    @Test
    public void getSingleProduct() {

        Response response = given().log().uri()
                .accept(ContentType.JSON).pathParam("id", 4)
                .when().get("/products/{id}").prettyPeek();

        //- Status code should be 200
        assertEquals(200,response.statusCode());//op1
        assertEquals(HttpStatus.SC_OK,response.statusCode());//op2

        //     - Content Type is application/json
        assertEquals("application/json",response.contentType());//op1
        assertEquals(ContentType.JSON.toString(),response.contentType());//op2

        //     - Print response
        response.prettyPrint();

        //     - id is 4
        int id = response.path("id");
        assertEquals(4, id);

        assertEquals(4, (Integer) response.path("id"));

        //     - Name is "Coconut"
        String name = response.path("name");
        assertEquals("Coconut",name);

        assertEquals("Coconut", response.path("name"));

        //     - Vendor name is "True Fruits Inc."
        Object vendorName = response.path("vendors[0].name");
        System.out.println("vendorName = " + vendorName);
        assertEquals("True Fruits Inc.",vendorName);

    }

    @Test
    public void getSingleProductJsonPath(){
        Response response = given().log().uri()
                .accept(ContentType.JSON).pathParam("id", 4)
                .when().get("/products/{id}").prettyPeek();

        JsonPath jp = response.jsonPath();

        assertEquals(4,jp.getInt("id"));
        assertEquals("Coconut",jp.getString("name"));
        assertEquals("True Fruits Inc.",jp.getString("vendors[0].name"));
    }

    @Test
    public void getSingleProductWithHamCrest(){
        given().log().uri()
                .accept(ContentType.JSON) // send me data in JSON format
                .pathParam("id", 4)
                .when().get("/products/{id}").prettyPeek()
                .then().statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", is(4))
                .body("name",is("Coconut"))
                .body("vendors[0].name",is("True Fruits Inc."));

    }

    @Test
    public void getSingleProductWithHamCrestPlusJsonPath(){

        JsonPath jp = getResponse("/products/{id}", 4);

        assertEquals(4,jp.getInt("id"));
        assertEquals("Coconut",jp.getString("name"));
        assertEquals("True Fruits Inc.",jp.getString("vendors[0].name"));

    }

    public static JsonPath getResponse(String endpoint, int pathParam){
        return given().log().uri()
                .accept(ContentType.JSON) // send me data in JSON format
                .pathParam("id", pathParam)
                .when().get(endpoint).prettyPeek()
                .then().statusCode(200)
                .contentType(ContentType.JSON)
                .extract().jsonPath();
    }

    /**     HOMEWORK
     *1- Given accept type is Json
     *2- Path Parameters value is
     *     - id —> 2
     *3- Query Parameter start  value  is 1
     *4- Query Parameter limit  value  is 100
     *5- When user sends GET request to /vendors/{id}/products
     *6- Verify followings
     *     - Status code should be 200
     *     - Content Type is application/json
     *     - verify limit is 100
     *     - verify start is 1
     *     - print all product names
     *
     */


}
