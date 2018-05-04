package org.ilite.frc.robot.modules;

import edu.wpi.first.wpilibj.Relay;
import org.ilite.frc.robot.Constants;

public class SignalLight implements IModule {

    private Relay signalLight;
    private SignalState currentState;
    private long blinkStartTime;
    private boolean isBlinkingOn;

    public enum SignalState {
        OFF(Relay.Value.kOff, -1.0), READY_TO_FIRE(Relay.Value.kOn, 0.3);

        Relay.Value relayValue;
        double blinkTime;

        SignalState(Relay.Value relayValue, double delay) {
            this.relayValue = relayValue;
            this.blinkTime = delay;
        }
    }

    public SignalLight() {
        signalLight = new Relay(Constants.LED_RELAY);
    }

    @Override
    public void init() {
        currentState = SignalState.OFF;
    }

    @Override
    public boolean update() {
        switch(currentState) {
            case READY_TO_FIRE:
                if(!isBlinkingOn && System.currentTimeMillis() - blinkStartTime >= currentState.blinkTime) {
                    isBlinkingOn = true;
                    blinkStartTime = System.currentTimeMillis();
                    signalLight.set(currentState.relayValue);
                } else {
                    isBlinkingOn = false;
                    signalLight.set(SignalState.OFF.relayValue);
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

}
