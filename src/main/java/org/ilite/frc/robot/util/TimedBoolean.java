package org.ilite.frc.robot.util;

public class TimedBoolean {

    private long triggerTime; // In MS

    public TimedBoolean(long delay) {
        this.reset(delay);
    }

    public boolean get() {
        if(System.currentTimeMillis() >= triggerTime) {
            return true;
        }
        return false;
    }

    public void reset(long delay) {
        this.triggerTime = System.currentTimeMillis() + delay;
    }

}
