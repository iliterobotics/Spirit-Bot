package org.ilite.frc.robot.modules;

import edu.wpi.first.wpilibj.Relay;
import org.ilite.frc.robot.Constants;

public class Horn implements IModule{

    private Relay hornRelay;

    private boolean startSequence;
    private long startTime, duration;
    private Relay.Value timedState;
    private Relay.Value desiredState;

    public Horn() {
        hornRelay = new Relay(Constants.RELAY_PORT_HORN);
    }


    @Override
    public void init() {
        startSequence = false;
        timedState = Relay.Value.kOff;
        startTime = 0;
        duration = 0;
    }

    @Override
    public boolean update() {
        if(System.currentTimeMillis() - startTime < duration && startSequence) {
            timedState = Relay.Value.kOn;
        } else {
            startSequence = false;
            timedState = Relay.Value.kOff;
        }

        if(startSequence) {
            hornRelay.set(timedState);
        } else {
            hornRelay.set(desiredState);
        }
        return false;
    }

    public void turnOn() {
        //desiredState = Relay.Value.kOn;
        desiredState = Relay.Value.kOff;
    }

    public void turnOff() {
        desiredState = Relay.Value.kOff;
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
