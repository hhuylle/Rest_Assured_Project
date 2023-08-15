package com.cydeo.day03;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P03_SpartanWithResponsePath extends SpartanTestBase {

      /*
     Given accept type is json
     And path param id is 10
     When user sends a get request to "api/spartans/{id}"
     Then status code is 200
     And content-type is "application/json"
     And response payload values match the following:
          id is 10,
          name is "Lorenza",
          gender is "Female",
          phone is 3312820936
   */

    @DisplayName("GET spartan with Response Path")
    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 10)
                .when()
                .get("api/spartans/{id}");

        response.prettyPrint();
        //verify status code
        assertEquals(200, response.statusCode());
        //verify content type
        assertEquals("application/json",response.contentType());

//        And response payload values match the following:
//                  id is 10,
//                name is "Lorenza",
//                gender is "Female",
//                phone is 3312820936
        int id = response.path("id");
        String name = response.path("name");
        String gender = response.path("gender");
        long phone = response.path("phone");

        //if the path is incorrect it will return null
        System.out.println("id = " + id);
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);

        String address = response.path("address");
        System.out.println("address = " + address);

        //Assertions
        assertEquals(10,id);
        assertEquals("Lorenza",name);
        assertEquals("Female", gender);
        assertEquals(3312820936l,phone);

    }

    @DisplayName("GET all Spartans")
    @Test
    public void test2(){

        Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans");
        //response.prettyPrint();

        //get me first spartan ID
        System.out.println("first spartan ID = " + response.path("id[0]"));
        System.out.println("first spartan ID = " + response.path("[0].id"));


        //get first spartan name
        System.out.println("first spartan name = " + response.path("name[0]"));
        System.out.println("first spartan name = " + response.path("[0].name"));

        //get me last spartan
        //name[-1] OR [-1].name --> -1 refers last element of the name list
        System.out.println("last spartan name = "+response.path("name[-1]"));


        //get me second spartan name from the last
        System.out.println("second spartan name from the last = " + response.path("name[-3]"));

        //get me all spartan names
        System.out.println("All spartan names = "+ response.path("name"));
        List<String> allNames = response.path("name");

        //how to print all names
        for (String eachName : allNames) {
            System.out.println(eachName);
        }

    }

}
