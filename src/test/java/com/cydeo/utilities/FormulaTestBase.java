package com.cydeo.utilities;

import org.junit.jupiter.api.BeforeAll;
import static io.restassured.RestAssured.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class FormulaTestBase {

    @BeforeAll
    public static void init(){
        baseURI="http://ergast.com/api/f1";
    }

    @AfterAll
    public static void destroy(){
        reset();
    }
}
