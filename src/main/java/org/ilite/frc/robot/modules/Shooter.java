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
		this.pressureSensor = pressureSensor;
		this.pot = pot;
		this.elevateMotor = new Talon(Constants.TALON_PWM_PORT_ELEVATE);
		this.shootRelay = new Relay(Constants.RELAY_PORT_SHOOTER);
		this.dumpRelay = new Relay(Constants.RELAY_PORT_DUMP);
		this.ledRelay = new Relay(Constants.RELAY_PORT_LED);
		this.limitSwitch = new DigitalInput(Constants.DIO_PORT_ELEVATION_LIMIT_SWITCH);

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
		if ((mCurrentTime - mStartTime) % 200 < 50)
		{
			System.out.printf("Angle: %s\n", mAngle);
		}
		blinkLED();
		return false;
	}

	public boolean dump() {
		
		if(mPressureSensor.getPSI() > 70) {	//pressure needed to dump
			mDumpRelay.set(Relay.Value.kOn);  // On state.
	    }
		
		return true;
	}

	public void blinkLED() {
		if (isAtPressure() && currentTime - startTime > 1000) {
			if (ledRelay.get().equals(Relay.Value.kOn)) {
				ledRelay.set(Relay.Value.kOff);
			}
			else {
				mLedRelay.set(Relay.Value.kOn);
			}
			mStartTime = mCurrentTime;

		}
	}

	public boolean shoot() {
		if(!mLimitSwitch.get() && mPressureSensor.getPSI() > 60 ) { //Don't shoot unless limit switch is inactive
			mShootRelay.set(Relay.Value.kOn);// On state.
		}
		return true;
	}

	public void shootRelayOff() {
		mShootRelay.set(Relay.Value.kOff);
	}

	public void dumpRelayOff() {
		mDumpRelay.set(Relay.Value.kOff);
	}

	public void setOutput(double output) {
		double threshold = 5;
//		
//		  if( Math.abs(angle - Constants.POT_LIMIT_1) < threshold || Math.abs(angle -
//		  Constants.POT_LIMIT_2) < threshold) { elevateMotor.set(0); }
//		 
		if ((!mLimitSwitch.get()) && output > 0) {
			mElevateMotor.set(0);
		} else {
			mElevateMotor.set(output);
		}
	}

	public boolean isAtPressure() {
		return pressureSensor.getPSI() < Constants.PSI_THRESHOLD;
	}

}
