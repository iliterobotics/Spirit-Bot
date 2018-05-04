package org.ilite.frc.robot.modules;

import edu.wpi.first.wpilibj.Relay;
import org.ilite.frc.robot.Constants;
import org.ilite.frc.robot.util.TimedBoolean;

public class SignalLight implements IModule {

    private Relay signalLight;
    private SignalState currentState;
    private TimedBoolean timer; // Returns true when time to transition from off to on or on to off
    private boolean isBlinkingOn;

    /**
     * Allows us to define values for on/off in one place in case we find out that, for example, "on" is Value.kForward.
     * Also defines how long we blink on/off for.
     */
    public enum SignalState {
        OFF(Relay.Value.kOff, -1), READY_TO_FIRE(Relay.Value.kOn, 300);

        Relay.Value relayValue;
        long blinkTime;

        SignalState(Relay.Value relayValue, long delay) {
            this.relayValue = relayValue;
            this.blinkTime = delay;
        }
    }

    public SignalLight() {
        signalLight = new Relay(Constants.LED_RELAY);
        timer = new TimedBoolean(0);
    }

    @Override
    public void init() {
        currentState = SignalState.OFF;
        isBlinkingOn = false;
        timer.reset(0);
    }

    @Override
    public boolean update() {
        switch(currentState) {
            case READY_TO_FIRE:
                if(timer.get()) { // If timer is done
                    timer.reset(currentState.blinkTime); // Reset the timer
                    toggleLight(currentState, isBlinkingOn); // Toggle light on/off
                }
                break;
            case OFF:
            default:
                signalLight.set(currentState.relayValue);
                isBlinkingOn = false;
                break;
        }
        return false;
    }

    public void setState(SignalState desiredState) {
        currentState = desiredState;
    }

    /**
     * Toggles light between an "on" state and the "off" state
     * @param currentState The state describing what "on" is
     * @param isLightOn Whether the physical light is on or not.
     */
    private void toggleLight(SignalState currentState, boolean isLightOn) {
        if(isLightOn) { // Toggle light on/off
            signalLight.set(SignalState.OFF.relayValue);
        } else {
            signalLight.set(currentState.relayValue);
        }
    }

}
