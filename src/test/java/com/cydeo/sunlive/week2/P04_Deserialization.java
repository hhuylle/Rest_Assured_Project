package com.cydeo.sunlive.week2;

import com.cydeo.utilities.FruitTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.*;

public class P04_Deserialization extends FruitTestBase {

    /**
     * Send request to FruitAPI url and save the response
     * Accept application/json
     * GET /customers
     * Store the response in Response Object that comes from get Request
     * Print out followings
     *     - Print response
     *     - Content-Type is application/json
     *     - Status Code is 200
     *     - Retrieve data as JAVA Collections and print out following informations
     *
     System.out.println("====== GET META ======");
     System.out.println("====== GET LIMIT ======");
     System.out.println("====== GET CUSTOMERS ======");
     System.out.println("====== GET FIRST CUSTOMER ======");
     System.out.println("====== PRINT CUSTOMERS IDs ======");
     System.out.println("====== PRINT CUSTOMERS Names ======");

     */

    @Test
    public void getCustomers() {
        JsonPath jp = given().log().uri()
                .accept(ContentType.JSON)
                .when().get("/customers")
                //.prettyPeek() //// to continue method changing while printing
                // we can use prettyPeek() --> Return type is still response
                .then().statusCode(200).contentType("application/json").extract().jsonPath();

        System.out.println("====== GET ALL DATA ======");
        Map<String, Object> allData = jp.getMap("");
        System.out.println("allData = " + allData);

        System.out.println("====== GET META ======");
        Map<String, Integer> allMeta = (Map<String, Integer>) allData.get("meta");
        System.out.println("allMeta = " + allMeta);

        System.out.println("====== GET LIMIT ======");
        System.out.println("allMeta.get(\"limit\") = " + allMeta.get("limit"));

        System.out.println("====== GET CUSTOMERS ======");
        List<Map<String, Object>> allCustomers = (List<Map<String, Object>>) allData.get("customers");
        System.out.println("customers = " + allCustomers);

        System.out.println("====== GET FIRST CUSTOMER ======");
        Map<String, Object> firstCustomer = allCustomers.get(0);
        System.out.println("customers.get(0) = " + allCustomers.get(0));

        System.out.println("====== PRINT FIRST CUSTOMERS ID ======");
        System.out.println("firstCustomer.get(\"id\") = " + firstCustomer.get("id"));

        System.out.println("====== PRINT CUSTOMERS IDs ======");
        //List<Object> customerIds = jp.getList("customers.id");
        //System.out.println("customerIds = " + customerIds);


        List<Integer> IDs = allCustomers.stream().map(e -> (Integer)e.get("id")).collect(Collectors.toList());
        System.out.println("IDs = " + IDs);

        //use for loop to get each customer IDs
        List<Integer> allIDs = new ArrayList<>();

        for (Map<String, Object> eachCustomer : allCustomers) {
            //System.out.println("eachCustomer.get(\"id\") = " + eachCustomer.get("id"));
            Object id = eachCustomer.get("id");
            allIDs.add((Integer)id);
        }

        System.out.println(allIDs);

        System.out.println("====== PRINT CUSTOMERS Names ======");
        List<String> customerNames = allCustomers.stream().map(n->(String)n.get("name")).collect(Collectors.toList());
        System.out.println("names = " + customerNames);


        List<String> allNames = new ArrayList<>();

        for (Map<String, Object> eachName : allCustomers) {
            //System.out.println("eachName.get(\"name\") = " + eachName.get("name"));
            Object names = eachName.get("name");
            allNames.add((String)names);
        }

        System.out.println("allNames = " + allNames);


    }
}
