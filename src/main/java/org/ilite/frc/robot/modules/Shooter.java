package org.ilite.frc.robot.modules;

import org.ilite.frc.robot.Constants;
import org.ilite.frc.robot.sensors.Potentiometer;
import org.ilite.frc.robot.sensors.PressureSensor;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Talon;

public class Shooter implements IModule {

	private Talon mElevateMotor;
	private PressureSensor mPressureSensor;
	private Potentiometer mPot;
	private Relay mShootRelay;
	private Relay mDumpRelay;
	private double mAngle;
	private DigitalInput mLimitSwitch;
	private Relay mLedRelay;
	private long mStartTime;
	private long mCurrentTime;

	public Shooter(PressureSensor pressureSensor, Potentiometer pot) {
		this.mPressureSensor = pressureSensor;
		this.mPot = pot;
		this.mElevateMotor = new Talon(Constants.TALON_PWM_PORT_ELEVATE);
		this.mShootRelay = new Relay(Constants.RELAY_PORT_SHOOTER);
		this.mDumpRelay = new Relay(Constants.RELAY_PORT_DUMP);
		this.mLedRelay = new Relay(Constants.RELAY_PORT_LED);
		this.mLimitSwitch = new DigitalInput(Constants.DIO_PORT_ELEVATION_LIMIT_SWITCH);

	}

	@Override
	public void init() {
		this.mAngle = mPot.getAngle();
		mStartTime = System.currentTimeMillis();
		mCurrentTime = System.currentTimeMillis();
	}

	@Override
	public boolean update() {
		mCurrentTime = System.currentTimeMillis();
		this.mAngle = mPot.getAngle();
		if(isDown()) mPot.zeroAngle();
		if ((mCurrentTime - mStartTime) > 100)
		{
			mStartTime = System.currentTimeMillis();
			System.out.printf("Potentiometer Angle: %s\nPressure: %s\nOffset: %s\n", mPot.getAngle(), mPressureSensor.getPSI(), mPressureSensor.getOffset(30));
		}
		blinkLED();
		return false;
	}

    public void blinkLED() {
        if (isAtShootingPressure() && mCurrentTime - mStartTime > 300) {
            if (mLedRelay.get().equals(Relay.Value.kOn)) {
                mLedRelay.set(Relay.Value.kOff);
            }
            else {
                mLedRelay.set(Relay.Value.kOn);
            }
            mStartTime = mCurrentTime;

        }
    }

	public boolean shoot() {
		//if(!mLimitSwitch.get()) { //Don't shoot unless limit switch is inactive
			mShootRelay.set(Relay.Value.kOn);// On state.
		return true;
	}

	public void shootRelayOff() {
		mShootRelay.set(Relay.Value.kOff);
	}

	public boolean dump() {
		//if(mPressureSensor.getPSI() < 70) {	//pressure needed to dump
		mDumpRelay.set(Relay.Value.kOn);  // On state.
		//}
		return true;
	}

	public void dumpRelayOff() {
		mDumpRelay.set(Relay.Value.kOff);
	}

	public void setOutput(double output) {
//		
//		  if( Math.abs(angle - Constants.POT_LIMIT_1) < threshold || Math.abs(angle -
//		  Constants.POT_LIMIT_2) < threshold) { elevateMotor.set(0); }
//
		if(isDown()) {
			if(output > 0) output = 0;
		}
		mElevateMotor.set(output);
	}

	public boolean isDown() {
		return !mLimitSwitch.get();
	}

	public boolean isAtShootingPressure() {
		return mPressureSensor.getPSI() > Constants.PSI_THRESHOLD;
	}

}
