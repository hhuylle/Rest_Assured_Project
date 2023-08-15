package com.cydeo.day05;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class P01_HamCrestMatchersIntro {
    
    @Test
    public void numbers(){

        //Junit5 assert equals methods
        assertEquals(9, 6+3);

        //Hamcrest Matchers come from RestAssured dependency
        //2 static import to get rid of class names ,directly call assertThat and Matchers
        //import static org.hamcrest.MatcherAssert.*;
        //import static org.hamcrest.Matchers.*;

        //Hamcrest Matchers
        //Matchers have 2 overloaded method
        //First one will take value to check
        //Second one will take another Matchers to make it readable/ to add new assert functionality

        /**
         is(someValue)
         is(equalTo(someValue))
         equalTo(someValue)
         All of them same in terms of assertion
         */
        assertThat(6+3, is(9));
        assertThat(6+3,is(equalTo(9)));
        assertThat(6+3,equalTo(9));

        /**
         * They are all the same for assertion
         */
        assertThat(5+5,not(9));
        assertThat(5+5,is(not(9)));
        assertThat(5+5,is(not(equalTo(9))));

        assertThat(5+6,is(greaterThan(10)));
        assertThat(5+6,is(greaterThanOrEqualTo(11)));
        assertThat(5+6,greaterThan(10));
        assertThat(5+6,lessThan(12));
        assertThat(5+6,lessThanOrEqualTo(11));

    }

    @Test
    public void testString(){

        String msg = "API is fun!";

        assertThat(msg,is("API is fun!"));
        assertThat(msg,equalTo("API is fun!"));
        assertThat(msg,equalToIgnoringCase("api is FUN!"));

        assertThat(msg,startsWith("API"));
        assertThat(msg,startsWithIgnoringCase("api"));

        assertThat(msg,endsWith("fun!"));
        assertThat(msg,endsWithIgnoringCase("FUN!"));

        assertThat(msg,containsString("is"));
        assertThat(msg,containsStringIgnoringCase("IS"));

        assertThat(msg,not("FUN!"));
        assertThat(msg,is(not("FUN!")));

    }

    @Test
    public void testCollections(){

        List<Integer> numberList = Arrays.asList(3,5,1,77,44,76);

        //hasSize() method to check size of elements
        assertThat(numberList,hasSize(6));

        //hasItem() method to check 77 is into the collection
        assertThat(numberList, hasItem(77));

        //hasItems() method to check 44 and 76 are into the collection
        assertThat(numberList,hasItems(44,76,5));

        //lopp through each of the elements & make sure they are matching w/ Matchers inside the everyItem
        //to check all elements >= 1
        assertThat(numberList,everyItem(greaterThanOrEqualTo(1)));
        assertThat(numberList,everyItem(lessThanOrEqualTo(77)));

        //containsInRelativeOrder() checks if you have values and their position is correct
        assertThat(numberList,containsInRelativeOrder(3,5,1,77,44,76));

        //containsInAnyOrder() checks if you have all the values, position might be different
        assertThat(numberList,containsInAnyOrder(1,3,5,44,76,77));



    }
}
