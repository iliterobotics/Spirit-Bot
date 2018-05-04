package org.ilite.frc.robot.utils;

import org.ilite.frc.robot.util.TimedBoolean;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TimedBooleanTest {

    @Test
    public void testTiming() throws InterruptedException {
        TimedBoolean timer = new TimedBoolean(1000);

        assertEquals(false, timer.get());

        Thread.sleep(1001);

        assertEquals(true, timer.get());

    }

    @Test
    public void testReset() throws InterruptedException {
        TimedBoolean timer = new TimedBoolean(1000);

        Thread.sleep(1001);

        assertEquals(true, timer.get());

        timer.reset(1000);

        assertEquals(false, timer.get());
    }

}
