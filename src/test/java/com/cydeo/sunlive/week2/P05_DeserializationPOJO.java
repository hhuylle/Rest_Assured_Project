package com.cydeo.sunlive.week2;

import com.cydeo.pojo.MRData;
import com.cydeo.pojo.Status;
import com.cydeo.pojo.StatusTable;
import com.cydeo.utilities.FormulaTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class P05_DeserializationPOJO extends FormulaTestBase {

    /*
        - ERGAST API
        - Given accept type is json
        - When user send request /status.json
        - Then verify status code is 200
        - And content type is application/json; charset=utf-8
        - And total is 137
        - And limit is 30
        - And each status has statusId
     */

    @Test
    public void statusPOJO() {
        JsonPath jp = given().log().uri()
                .accept(ContentType.JSON)
                .when().get("/status.json")
                .then()
                .statusCode(200)
                .contentType("application/json; charset=utf-8")
                .extract().jsonPath();

        //DO DESERIALIZATION
        MRData mrData = jp.getObject("MRData", MRData.class);
        System.out.println("mrData = " + mrData);

        System.out.println("GET ME STATUS TABLE");
        StatusTable statusTable = mrData.getStatusTable();
        System.out.println("statusTable = " + statusTable);

        System.out.println("GET ME STATUS LIST");
        List<Status> statusList = statusTable.getStatusList();
        System.out.println("statusList = " + statusList);

        System.out.println("GET ME FIRST STATUS");
        System.out.println("statusList.get(0).getStatus() = " + statusList.get(0).getStatus());

        System.out.println("GET ME FIRST ID");
        System.out.println("statusList.get(0).getStatusId() = " + statusList.get(0).getStatusId());


    }
}
