package com.cydeo.utilities;



import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public abstract class HrTestBase {

    @BeforeAll
    public static void init(){
        baseURI = "http://54.165.189.254:1000/ords/hr";
        //MyIpAddress:1000/ords/hr
    }

    @AfterAll
    public static void destroy(){
        reset(); // RestAssured.reset();
        // It resets your BaseURI/BasePath etc to DEFAULT values from Rest Assured
    }


}
