package com.cydeo.utilities;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
public class BookItUtil {

    public static String getToken(String email, String password){

        JsonPath jsonPath = given().accept(ContentType.JSON)
                .queryParam("email", email)
                .queryParam("password", password)
                .when()
                .get("/sign")
                .then()
                .statusCode(200)
                .extract().jsonPath();

        //get the key out of json body
        String accessToken = jsonPath.getString("accessToken");

        return "Bearer "+accessToken;

         /*
         the proper way to handle different token is
         you need to have each user type in your configuration.properties
         then one method where you pick userType, and it will return token to you.

         getTokenByUser(String userType) "TEAM_LEADER","TEAM_MEMBER","TEACHER"

         switch(userType)
         case "TEAM_LEADER":
            String email = ConfigurationReader.getProperty("team-leader-email");
            String password = ConfigurationReader.getProperty("team-leader-password");

            .
            .
            .

            send request with given and email and password

                 JsonPath jsonPath = given().accept(ContentType.JSON)
                .queryParam("email", email)
                .queryParam("password", password)
                .when()
                .get("/sign")
                .then()
                .statusCode(200)
                .extract().jsonPath();

        //get the key out of json body
        String accessToken = jsonPath.getString("accessToken");

        return "Bearer "+accessToken;

     */
    }
}
