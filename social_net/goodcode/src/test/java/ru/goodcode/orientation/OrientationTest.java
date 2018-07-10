package ru.goodcode.orientation;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class OrientationTest {
    @Test
    public void clockwith() {
        assertTrue("N - W", Orientation.EAST == Orientation.clockwith(Orientation.NORTH));
        assertTrue("W - S", Orientation.SOUTH == Orientation.clockwith(Orientation.EAST));
        assertTrue("S - E", Orientation.WEST == Orientation.clockwith(Orientation.SOUTH));
        assertTrue("E - N", Orientation.NORTH == Orientation.clockwith(Orientation.WEST ));
    }

}