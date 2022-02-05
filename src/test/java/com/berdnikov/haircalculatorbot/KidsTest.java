package com.berdnikov.haircalculatorbot;

import junit.framework.TestCase;

public class KidsTest extends TestCase {

    public void testGetPrice() {
        Kids kids = new Kids();
        assertEquals("8.25",kids.getPrice("/k 30 3"));
    }
}