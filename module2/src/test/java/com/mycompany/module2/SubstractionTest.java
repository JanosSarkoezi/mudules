package com.mycompany.module2;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author saj
 */
public class SubstractionTest {

    private Substraction cut;

    @Before
    public void setUp() {
        cut = new Substraction();
    }

    @After
    public void tearDown() {
        cut = null;
    }

    @Test
    public void calculate() {
        Assert.assertEquals(2, cut.calculate(5, 3).intValue());
    }
}
