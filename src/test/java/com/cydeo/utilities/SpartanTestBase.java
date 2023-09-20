package com.cydeo.utilities;

import io.restassured.RestAssured;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;

public abstract class SpartanTestBase {

    public Logger log = LogManager.getLogger(this.getClass());

    @BeforeAll
    public static void init(){
        baseURI = "http://54.165.189.254:8000";
    }



}
