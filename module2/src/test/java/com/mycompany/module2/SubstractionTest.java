package com.mycompany.module2;

import junit.framework.TestCase;

/**
 *
 * @author saj
 */
public class SubstractionTest extends TestCase {

    private Substraction cut;

    @Override
    protected void setUp() throws Exception {
        cut = new Substraction();
    }
    
    @Override
    protected void tearDown() throws Exception {
        cut = null;
    }
    
    public void testCalculate() {
        assertEquals(2, cut.calculate(5, 3).intValue());
    }
}
