package com.cydeo.homework;

import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class Homework2 extends HrTestBase {

    //- Given accept type is Json
    //- Path param value- US
    //- When users sends request to /countries
    //- Then status code is 200
    //- And Content - Type is Json
    //- And country_id is US
    //- And Country_name is United States of America
    //And Region_id is 2
    @Test
    public void task1(){
        given().accept(ContentType.JSON)
                .when().get("/countries")
                .then().statusCode(200)
                .contentType(ContentType.JSON).and()
                .body("items.country_id",hasItem("US"))
                .body("items.country_name",hasItem("United States of America"))
                .body("items.region_id",hasItem(2));


    }

    //- Given accept type is Json
    //- Query param value - q={"department_id":80}
    //- When users sends request to /employees
    //- Then status code is 200
    //- And Content - Type is Json
    //- And all job_ids start with 'SA'
    //- And all department_ids are 80
    //- Count is 25
    @Test
    public void task2(){
        Response response = given().accept(ContentType.JSON)
                .queryParam("q", "{\"department_id\":80}")
                .when().get("/employees")
                .then().statusCode(200).contentType("application/json")
                .extract().response();
        JsonPath jp = response.jsonPath();
        List<Object> allJobIDs = jp.getList("items.job_id");
        System.out.println("allJobIDs = " + allJobIDs);

        List<Integer> allDepartmentIDs = jp.getList("items.department_id");
        System.out.println("allDepartmentIDs = " + allDepartmentIDs);

        System.out.println("jp.get(\"count\") = " + jp.get("count"));


    }

    //- Given accept type is Json
    //- Query param value q={"region_id":3}
    //- When users sends request to /countries
    //- Then status code is 200
    //- And all regions_id is 3
    //- And count is 6
    //- And hasMore is false
    //- And Country_name are;
    //Australia,China,India,Japan,Malaysia,Singapore
    @Test
    public void task3(){
        JsonPath jp = given().accept(ContentType.JSON)
                .queryParam("q", "{\"region_id\":3}")
                .when().get("/countries")
                .then().statusCode(200)
                .extract().jsonPath();

        List<Integer> allRegionIDs = jp.getList("items.region_id");
        System.out.println("allRegionIDs = " + allRegionIDs);

        System.out.println("jp.get(\"count\") = " + jp.get("count"));

        System.out.println("jp.get(\"hasMore\") = " + jp.get("hasMore"));

        List<String> allRegionNames = jp.getList("items.country_name");
        System.out.println("allRegionNames = " + allRegionNames);

    }
}
