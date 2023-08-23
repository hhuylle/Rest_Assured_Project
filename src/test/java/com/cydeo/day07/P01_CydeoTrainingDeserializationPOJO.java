package com.cydeo.day07;

import com.cydeo.pojo.Student;
import com.cydeo.pojo.Students;
import com.cydeo.utilities.CydeoTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class P01_CydeoTrainingDeserializationPOJO extends CydeoTestBase {

    @DisplayName("GET /student/2")
    @Test
    public void test1() {
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 2)
                .when().get("/student/{id}");

        response.prettyPrint();

        //verify status code
        assertEquals(200, response.statusCode());

        JsonPath jsonPath = response.jsonPath();

        //Deserialize to student clas
        Student student = jsonPath.getObject("students[0]", Student.class);


        //        firstName Mark              -->students[0].firstName
        System.out.println("student.getFirstName() = " + student.getFirstName());

        //        batch 13                    -->students[0].batch
        System.out.println("student.getBatch() = " + student.getBatch());

        //        major math                  -->students[0].major
        System.out.println("student.getMajor() = " + student.getMajor());

        //        emailAddress mark@email.com -->students[0].contact.
        System.out.println("student.getContact().getEmailAddress() = " + student.getContact().getEmailAddress());

        //        companyName Cydeo           -->students[0].company.companyName
        System.out.println("student.getCompany().getCompanyName() = " + student.getCompany().getCompanyName());

        //        street 777 5th Ave          -->students[0].company.address.street
        System.out.println("student.getCompany().getAddress().getStreet() = " + student.getCompany().getAddress().getStreet());

        //        zipCode 33222               -->students[0].company.address.zipCode
        System.out.println("student.getCompany().getAddress().getZipCode() = " + student.getCompany().getAddress().getZipCode());

        assertEquals("Mark", student.getFirstName());
        assertEquals(13, student.getBatch());
        assertEquals("math", student.getMajor());
        assertEquals("mark@email.com", student.getContact().getEmailAddress());
        assertEquals("Cydeo",student.getCompany().getCompanyName());
        assertEquals("777 5th Ave", student.getCompany().getAddress().getStreet());
        assertEquals(33222, student.getCompany().getAddress().getZipCode());
    }

    @DisplayName("GET /student/2")
    @Test
    public void test2() {
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 2)
                .when().get("/student/{id}");

        response.prettyPrint();

        //verify status code
        assertEquals(200, response.statusCode());

        JsonPath jsonPath = response.jsonPath();

        //Deserialize to students class
        Students students = jsonPath.getObject("",Students.class);

        //we deserialize everything to Students class which is holding list of student
        System.out.println("students = " + students);
        Student student = students.getStudents().get(0);

        //if there is no path, we can use response .as method for deserialization
        //Students studentsWithAs = response.as(Students.class);


        //        firstName Mark              -->students[0].firstName
        System.out.println("student.getFirstName() = " + student.getFirstName());

        //        batch 13                    -->students[0].batch
        System.out.println("student.getBatch() = " + student.getBatch());

        //        major math                  -->students[0].major
        System.out.println("student.getMajor() = " + student.getMajor());

        //        emailAddress mark@email.com -->students[0].contact.
        System.out.println("student.getContact().getEmailAddress() = " + student.getContact().getEmailAddress());

        //        companyName Cydeo           -->students[0].company.companyName
        System.out.println("student.getCompany().getCompanyName() = " + student.getCompany().getCompanyName());

        //        street 777 5th Ave          -->students[0].company.address.street
        System.out.println("student.getCompany().getAddress().getStreet() = " + student.getCompany().getAddress().getStreet());

        //        zipCode 33222               -->students[0].company.address.zipCode
        System.out.println("student.getCompany().getAddress().getZipCode() = " + student.getCompany().getAddress().getZipCode());

        assertEquals("Mark", student.getFirstName());
        assertEquals(13, student.getBatch());
        assertEquals("math", student.getMajor());
        assertEquals("mark@email.com", student.getContact().getEmailAddress());
        assertEquals("Cydeo",student.getCompany().getCompanyName());
        assertEquals("777 5th Ave", student.getCompany().getAddress().getStreet());
        assertEquals(33222, student.getCompany().getAddress().getZipCode());
    }

    @DisplayName("GET /student/2")
    @Test
    public void test3() {
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", 2)
                .when().get("/student/{id}");

        response.prettyPrint();

        //verify status code
        assertEquals(200, response.statusCode());
        JsonPath jsonPath = response.jsonPath();

        com.cydeo.pojo.ready.Student student = jsonPath.getObject("students[0]", com.cydeo.pojo.ready.Student.class);

        System.out.println("student.getJoinDate() = " + student.getJoinDate());
        System.out.println("student.getCompany().getStartDate() = " + student.getCompany().getStartDate());
        System.out.println("student.getCompany().getAddress().getState() = " + student.getCompany().getAddress().getState());

    }
}
