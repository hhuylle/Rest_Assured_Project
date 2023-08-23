package com.cydeo.utilities;

import org.junit.jupiter.api.BeforeAll;
import static io.restassured.RestAssured.*;

public abstract class SpartanAuthTestBase {

    @BeforeAll
    public static void init(){
        baseURI="http://54.165.189.254:7000";
    }
}
