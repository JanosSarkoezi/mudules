package com.mycompany.module1;

import junit.framework.TestCase;

/**
 *
 * @author saj
 */
public class AdditionTest extends TestCase {

    private Addition cut;

    @Override
    protected void setUp() throws Exception {
        cut = new Addition();
    }
    
    @Override
    protected void tearDown() throws Exception {
        cut = null;
    }
    
    public void testCalculate() {
        assertEquals(5, cut.calculate(2, 3).intValue());
    }
}
