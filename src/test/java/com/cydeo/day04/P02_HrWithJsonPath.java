package com.cydeo.day04;

import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class P02_HrWithJsonPath extends HrTestBase {

    @DisplayName("GET all /countries")
    @Test
    public void test1(){

        Response response = get("/countries");

        //response.prettyPrint();
        
        //verify status code
        assertEquals(200, response.statusCode());

        //Create Jsonpath object
        JsonPath jsonPath = response.jsonPath();

        //get 3rd country name
        System.out.println("jsonPath.getString(\"items[2].country_name\") = " + jsonPath.getString("items[2].country_name"));

        //get me 3rd and 4th country name
        System.out.println("jsonPath.getString(\"items[2].country_name\") = " + jsonPath.getString("items[2,3].country_name"));

        //get me all countries name where region_id is 2
        List<String> list = jsonPath.getList("items.findAll {it.region_id==2}.country_name");
        System.out.println(list);



    }

    /*
            Given accept type is application/json
            And query param limit is 200
            When user send request /employees
            Then user should see .....
             */

    @DisplayName("GET all /employees?limit=200 with JsonPath")
    @Test
    public void test2(){

        Response response = given().accept(ContentType.JSON)
                .and()
                .queryParam("limit", 200)
                .when().get("/employees");

        assertEquals(200,response.statusCode());

        //create jsonpath object
        JsonPath jsonPath = response.jsonPath();

        //get all emails from response
        //System.out.println("jsonPath.get(\"items.emails\") = " + jsonPath.get("items.email"));
        List<Object> allEmails = jsonPath.getList("items.email");
        System.out.println("allEmails = " + allEmails);
        System.out.println("allEmails.size() = " + allEmails.size());

        //get all emails who is working as IT_PROG
        List<String> emailsIT = jsonPath.getList("items.findAll {it.job_id=='IT_PROG'}.email");
        System.out.println("emailsIT = " + emailsIT);

        //get all employees firstname whose salary is more than 10000
        List<String> allFirstname = jsonPath.getList("items.findAll {it.salary>10000}.first_name");
        System.out.println("allFirstnameHasSalaryMoreThan10k = " + allFirstname);

        //get all info from response who has max salary
        System.out.println("jsonPath.getString(\"items.max {it.salary}\") = " + jsonPath.getString("items.max {it.salary}"));

        //get firstname from response who has max salary
        System.out.println("firstname from response who has max salary = " + jsonPath.getString("items.max {it.salary}.first_name"));

        //get firstname from response who has min salary
        System.out.println("firstname from response who has min salary = " + jsonPath.getString("items.min {it.salary}.first_name"));


    }


        /*

    TASK
    Given
             accept type is application/json
     When
             user sends get request to /locations
     Then
             response status code must be 200
             content type equals to application/json
             get the second city with JsonPath
             get the last city with JsonPath
             get all country ids
             get all city where their country id is UK

      */

    @DisplayName("GET /locations")
    @Test
    public void test3(){

        Response response = given().accept(ContentType.JSON)
                .when().get("/locations");

        //response status code must be 200
        assertEquals(200,response.statusCode());

        //content type equals to application/json
        assertEquals("application/json",response.contentType());

        //get the second city with JsonPath
        JsonPath jsonPath = response.jsonPath();
        System.out.println("second city = " + jsonPath.getString("items[1].city"));

        //get the last city with JsonPath
        System.out.println("last city = " + jsonPath.getString("items[-1].city"));

        //get all country ids
        List<Object> allCountryID = jsonPath.getList("items.country_id");
        System.out.println("allCountryID = " + allCountryID);

        //get all city where their country id is UK
        List<Object> allCityInUK = jsonPath.getList("items.findAll {it.country_id=='UK'}.city");
        System.out.println("allCityInUK = " + allCityInUK);


    }

}
