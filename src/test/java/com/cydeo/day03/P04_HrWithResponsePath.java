package com.cydeo.day03;

import com.cydeo.utilities.HrTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class P04_HrWithResponsePath extends HrTestBase{

    @DisplayName("GET Request to countries with using Response Path")
    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON).when().get("/countries");
        //response.prettuyPrint();

        //print limit
        System.out.println("limit = " + response.path("limit"));

        //print hasMore
        System.out.println("hasMore = " + response.path("hasMore"));

        //print second country name
        System.out.println("second country name = "+response.path("items[1].country_name"));

        //print 4th element country name
        System.out.println("second country name = "+response.path("items[3].country_name"));

        //print 3rd element href
        System.out.println("3rd element href = "+ response.path("items[2].links[0].href"));

        //get all countries names
        List<String> allCountriesNames = response.path("items.country_name");
        for (String eachCountryName : allCountriesNames) {
            System.out.println(eachCountryName);
        }

        //verify all region_ids equals to 2
        response = given().accept(ContentType.JSON)
                .queryParam("q","{\"region_id\":2}")
                .when().get("/countries");
        List<Integer> allRegionIDs = response.path("items.region_id");

        for (Integer id : allRegionIDs) {
            assertEquals(2,id);
            System.out.println("id = " + id);
        }

    }
}
