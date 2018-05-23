package org.ilite.frc.robot.modules;

import edu.wpi.first.wpilibj.Relay;
import org.ilite.frc.robot.Constants;

public class Horn implements IModule{

    private Relay hornRelay;

    private boolean startSequence;
    private long startTime, duration;

    public Horn() {
        hornRelay = new Relay(Constants.RELAY_PORT_HORN);
    }


    @Override
    public void init() {
        startSequence = false;
        startTime = 0;
        duration = 0;
    }

    @Override
    public boolean update() {
        if(System.currentTimeMillis() - startTime < duration && startSequence) {
            turnOn();
        } else {
            startSequence = false;
            turnOff();
        }
        return false;
    }

    public void turnOn() {
        hornRelay.set(Relay.Value.kOn);
    }

    public void turnOff() {
        hornRelay.set(Relay.Value.kOff);
    }

    public void sound(long time) {
        startSequence = true;
        duration = time;
        startTime = System.currentTimeMillis();
    }

    public boolean isInFiringSequence() {
        return startSequence;
    }

}
