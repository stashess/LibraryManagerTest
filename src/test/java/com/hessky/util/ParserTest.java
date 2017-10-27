package com.hessky.util;


import com.hessky.to.InputData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static com.hessky.util.StatusCode.*;
import static org.junit.Assert.assertTrue;

/**
 * Created by Stanislav Cheslavskyi on 05.10.2017.
 */
@RunWith(JUnit4.class)
public class ParserTest {


    @Test
    public void addTest() {
        assertTrue(Parser.parse("add author " + "\"" + "book" + "\"").equals(
                new InputData(ADD,"author","book")));
    }

    @Test
    public void editTest() {
        assertTrue(Parser.parse("edit book").equals(
                new InputData(EDIT,"book")));
    }

    @Test
    public void deleteTest() {
        assertTrue(Parser.parse("delete book").equals(
                new InputData(DELETE,"book")));
    }

    @Test
    public void getAllTest() {
        assertTrue(Parser.parse("all books").equals(
                new InputData(GET_All)));
    }

    @Test
    public void errorTest() {
        assertTrue(Parser.parse("addd author " + "\"" + "book" + "\"").equals(
                new InputData(INPUT_ERROR)));
    }

    @Test
    public void digitTest() {
        assertTrue(Parser.parse("").equals(
                new InputData(IGNORE)));
    }



}