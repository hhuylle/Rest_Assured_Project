package com.cydeo.day08;

import com.cydeo.utilities.BookItTestBase;
import com.cydeo.utilities.BookItUtil;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class P02_BookItTest extends BookItTestBase {

    //String accessToken = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxODMxNiIsImF1ZCI6InN0dWRlbnQtdGVhbS1sZWFkZXIifQ.AfWgzpG2pL-Sdq099hoRVsADQwTTrj6tCtYddW1wIRM";

    String email = "lfinnisz@yolasite.com";
    String password = "lissiefinnis";
    String accessToken = BookItUtil.getToken(email,password);

    @DisplayName("GET /api/campuses")
    @Test
    public void test1(){
        System.out.println("accessToken = " + accessToken);

        given().accept(ContentType.JSON)
                .header("Authorization",accessToken)
                .when().get("/api/campuses").prettyPeek()
                .then().statusCode(200);

        //Create new Util class and it will generate token based on provided email & password
        //BookItUtil.getToken(String email, String password

    }

    @DisplayName("GET /api/users/me")
    @Test
    public void test2(){

        given().accept(ContentType.JSON)
                .header("Authorization", BookItUtil.getToken(email, password))
                .get("/api/users/me").prettyPeek()
                .then().statusCode(200);
    }

    @DisplayName("GET /api/users/me")
    @Test
    public void test3(){

        given().accept(ContentType.JSON)
                .auth().oauth2(accessToken)
                .get("/api/users/me").prettyPeek()
                .then().statusCode(200);
    }
}
