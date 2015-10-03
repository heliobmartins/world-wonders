package com.digitalday.dojounittest;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Hélio on 03/10/2015.
 */
public class CalculatorTest {
    Calculator c;
    int a, b;

    @Before
    public void setUp() throws Exception {
        c = new Calculator();
        a = 99;
        b = 100;
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSum() throws Exception {
        c.sum(a, b);
        //assertEquals(x, new Double(199));
    }
}