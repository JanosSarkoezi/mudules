package com.mycompany.module1;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author saj
 */
public class AdditionTest {

    private Addition cut;

    @Before
    public void setUp() {
        cut = new Addition();
    }

    @After
    public void tearDown() {
        cut = null;
    }

    @Test
    public void calculate() {
        Assert.assertEquals(5, cut.calculate(2, 3).intValue());
    }
}
